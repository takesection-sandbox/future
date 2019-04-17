import Dependencies._

lazy val root = (project in file(".")).
  settings(
    organization := "com.pigumer",
    name := "future",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.12.7",
    libraryDependencies ++= Seq(
      scalaJava8Compat,
      slf4jSimple
    )
  )