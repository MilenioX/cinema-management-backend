import Dependencies._

ThisBuild / scalaVersion 	:= "2.13.10"
ThisBuild / organization 	:= "com.mundox"
ThisBuild / version 		:= "1.0.0-SNAPSHOT"

lazy val security = (project in file("security"))
    .settings(
        name := "Identity and Security",
        libraryDependencies ++= commonDependencies
    )

lazy val management = (project in file("management"))
    .settings(
        name := "Management",
        libraryDependencies ++= commonDependencies
    )

lazy val booking = (project in file("booking"))
    .settings(
        name := "Booking",
        libraryDependencies ++= commonDependencies
    )

lazy val billing = (project in file("billing"))
    .settings(
        name := "Booking",
        libraryDependencies ++= commonDependencies
    )
