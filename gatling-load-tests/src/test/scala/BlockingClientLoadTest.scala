package io.thingcare.load

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:8000")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("BasicSimulation")
    .exec(http("request_1")
      .get("/client"))
    .pause(1)

  setUp(scn.inject(
    constantUsersPerSec(1) during 10.seconds,
    constantUsersPerSec(5) during 10.seconds,
    constantUsersPerSec(10) during 10.seconds,
    constantUsersPerSec(20) during 10.seconds,
    constantUsersPerSec(50) during 10.seconds,
    constantUsersPerSec(100) during 10.seconds
    ,
    constantUsersPerSec(200) during 10.seconds,
    constantUsersPerSec(500) during 10.seconds,
    constantUsersPerSec(1000) during 10.seconds
    //        ,
    //    constantUsersPerSec(2000) during 10.seconds
  ).protocols(httpConf))

}