import sbt.*

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.15"

  lazy val scalaLog4jVersion = "12.0"
  lazy val log4jVersion = "2.19.0"
  lazy val akkaVersion = "2.7.0"
  lazy val akkaHttpVersion = "10.5.1"
  lazy val jacksonVersion = "2.15.1"

  lazy val log4jScala = "org.apache.logging.log4j" % "log4j-api-scala_2.13" % scalaLog4jVersion
  lazy val log4jCore = "org.apache.logging.log4j" % "log4j-core" % log4jVersion % Runtime
  lazy val akkaActorTyped = "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkaHttp =  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  lazy val jacksonModule = "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion

  lazy val portsDependencies: Seq[ModuleID] = Seq(
    akkaActorTyped, akkaStream, akkaHttp,
    jacksonModule
  )

  lazy val commonDependencies: Seq[ModuleID] = Seq(
    log4jScala, log4jCore,
    scalaTest % Test
  )
}
