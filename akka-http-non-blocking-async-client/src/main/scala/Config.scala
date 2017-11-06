import org.apache.http.impl.nio.client.HttpAsyncClients
import org.apache.http.nio.client.HttpAsyncClient

object Config {

  val asyncClient: HttpAsyncClient = {

    val client = HttpAsyncClients.custom()
      .setMaxConnTotal(Int.MaxValue)
      .setMaxConnPerRoute(Int.MaxValue)
      .build()

    client.start()
    client
  }


}
