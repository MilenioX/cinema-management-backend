import Dependencies._

ThisBuild / version := "1.0.0-SNAPSHOT"
ThisBuild / scalaVersion 	:= "2.13.10"
ThisBuild / organization 	:= "com.mundox"

lazy val core = (project in file("core"))
  .settings(
    name := "management-core",
    libraryDependencies ++= commonDependencies
  )

lazy val ports = (project in file("ports"))
  .aggregate(core)
  .dependsOn(core)
  .settings(
    name := "management-ports",
    libraryDependencies ++= commonDependencies ++ portsDependencies
  )