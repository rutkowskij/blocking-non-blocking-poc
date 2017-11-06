
name := "akka-http-non-blocking-async-client"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.apache.httpcomponents" % "httpasyncclient" % "4.1.2",
  "com.typesafe.akka" %% "akka-http" % "10.0.10",
  "org.apache.commons" % "commons-io" % "1.3.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "ch.qos.logback" % "logback-classic" % "1.1.8"
)