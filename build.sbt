val scala3Version = "3.7.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "aoc2024",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core"  % "2.12.0",
      "org.typelevel" %% "cats-parse" % "1.0.0",
      "org.scalameta" %% "munit"      % "1.0.0" % Test
    ),

    scalafmtOnCompile := true
  )