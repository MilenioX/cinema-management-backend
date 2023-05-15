ThisBuild / version := "1.0.0-SNAPSHOT"

lazy val core = (project in file("core"))
  .settings(
    name := "management-core"
  )

lazy val ports = (project in file("ports"))
  .aggregate(core)
  .dependsOn(core)
  .settings(
    name := "management-ports"
  )