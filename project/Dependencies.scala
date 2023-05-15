import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.15"

  lazy val akkaVersion = "2.7.0"
  lazy val akkaHttpVersion = "10.5.1"
  lazy val akkaActorTyped = "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkaHttp =  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion

  lazy val commonDependencies = Seq(
    akkaActorTyped,
    akkaStream,
    akkaHttp,
    scalaTest % Test)
}
