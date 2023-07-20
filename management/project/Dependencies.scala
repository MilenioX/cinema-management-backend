import sbt.*

object Dependencies {


  lazy val scalaTestVersion = "3.2.16"
  lazy val mockitoVersion = "3.2.15.0"
  lazy val scalaLog4jVersion = "12.0"
  lazy val log4jVersion = "2.19.0"
  lazy val catsVersion = "2.9.0"
  lazy val akkaVersion = "2.7.0"
  lazy val akkaHttpVersion = "10.5.1"

  lazy val pureConfigVersion = "0.17.4"

  lazy val log4jScala = "org.apache.logging.log4j" % "log4j-api-scala_2.13" % scalaLog4jVersion
  lazy val log4jCore = "org.apache.logging.log4j" % "log4j-core" % log4jVersion % Runtime
  lazy val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % Test
  lazy val mockito = "org.scalatestplus" %% "mockito-4-6" % mockitoVersion % Test
  lazy val cats = "org.typelevel" %% "cats-core" % catsVersion

  lazy val akkaActorTyped = "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkaStreamTest = "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion
  lazy val akkaHttp =  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  lazy val akkaHttpTest = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
  lazy val sprayJsonAkka = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
  lazy val pureConfig = "com.github.pureconfig" %% "pureconfig" % pureConfigVersion

  lazy val portsDependencies: Seq[ModuleID] = Seq(
    pureConfig,
    akkaActorTyped, akkaStream, akkaHttp,
    akkaStreamTest, akkaHttpTest,
    sprayJsonAkka,
  )

  lazy val commonDependencies: Seq[ModuleID] = Seq(
    cats,
    log4jScala, log4jCore,
    scalaTest, mockito,
  )
}
