
import java.util.UUID
import java.util.concurrent.atomic.AtomicInteger

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import org.apache.commons.io.IOUtils
import org.apache.http
import org.apache.http.client.methods.HttpGet
import org.apache.http.concurrent.FutureCallback
import org.apache.http.util.EntityUtils

import scala.concurrent.Promise
import scala.io.StdIn
import scala.util.{Failure, Success}

object Main extends LazyLogging {

  private val target = "http://127.0.0.1:9000/AkkaHttp"

  private val reqReceived = new AtomicInteger()
  private val reqFutureWaiting = new AtomicInteger()
  private val reqFutureCompleted = new AtomicInteger()

  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val route =
      path("client") {
        get {
          val promise = Promise[String]()
          val uuid = UUID.randomUUID().toString.substring(0, 8)
          logger.info(s"reqReceived: ${reqReceived.incrementAndGet()} uuid: $uuid")
          logger.trace(s"Request start: $uuid")
          val httpGet = new HttpGet(target)
          val futureCallback = new FutureCallback[http.HttpResponse] {
            override def failed(ex: Exception): Unit = promise.failure(ex)

            override def completed(result: http.HttpResponse): Unit = {
              promise.success(IOUtils.toString(result.getEntity.getContent))
              EntityUtils.consume(result.getEntity)
              logger.trace(s"reqFutureCompleted: ${reqFutureCompleted.incrementAndGet()}")
            }

            override def cancelled(): Unit = promise.failure(new RuntimeException("Cancelled"))
          }

          Config.asyncClient.execute(httpGet, futureCallback)
          logger.trace(s"reqFutureWaiting: ${reqFutureWaiting.incrementAndGet()}")
          onComplete(promise.future) {
            case Failure(exception) => {
              complete(HttpResponse(StatusCodes.InternalServerError, entity = exception.getMessage))
            }
            case Success(value) => {
              logger.trace(s"Request completed $uuid")
              complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, value))
            }
          }
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    logger.info(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

}


