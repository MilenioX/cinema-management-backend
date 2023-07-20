import sbt.*

object Dependencies {


  lazy val scalaTestVersion = "3.2.16"
  lazy val mockitoVersion = "3.2.15.0"
  lazy val scalaLog4jVersion = "12.0"
  lazy val log4jVersion = "2.19.0"
  lazy val catsVersion = "2.9.0"
  lazy val monixVersion = "3.4.0"
  lazy val akkaVersion = "2.7.0"
  lazy val akkaHttpVersion = "10.5.1"

  lazy val pureConfigVersion = "0.17.4"

  lazy val postgreSQL = "org.postgresql" % "postgresql" % "42.6.0"
  lazy val doobieVersion = "0.13.4"

  lazy val log4jScala = "org.apache.logging.log4j" % "log4j-api-scala_2.13" % scalaLog4jVersion
  lazy val log4jCore = "org.apache.logging.log4j" % "log4j-core" % log4jVersion % Runtime
  lazy val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % Test
  lazy val mockito = "org.scalatestplus" %% "mockito-4-6" % mockitoVersion % Test
  lazy val cats = "org.typelevel" %% "cats-core" % catsVersion

  lazy val monix = "io.monix" %% "monix" % monixVersion
  lazy val akkaActorTyped = "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkaStreamTest = "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion
  lazy val akkaHttp =  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  lazy val akkaHttpTest = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
  lazy val sprayJsonAkka = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
  lazy val pureConfig = "com.github.pureconfig" %% "pureconfig" % pureConfigVersion

  lazy val doobieCodeDep = "org.tpolecat" %% "doobie-core" % doobieVersion
  lazy val doobiePostgreSQL = "org.tpolecat" %% "doobie-postgres" % doobieVersion
  lazy val doobieSpecs2 = "org.tpolecat" %% "doobie-specs2" % "1.0.0-RC1" % "test"
  lazy val doobieScalaTest = "org.tpolecat" %% "doobie-scalatest" % "1.0.0-RC1" % "test"

  lazy val portsDependencies: Seq[ModuleID] = Seq(
    pureConfig,
    monix,
    akkaActorTyped, akkaStream, akkaHttp,
    akkaStreamTest, akkaHttpTest,
    sprayJsonAkka,
    doobieCodeDep, doobiePostgreSQL, doobieSpecs2, doobieSpecs2,
  )

  lazy val commonDependencies: Seq[ModuleID] = Seq(
    cats,
    log4jScala, log4jCore,
    scalaTest, mockito,
  )
}
