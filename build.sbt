name := "scalaclean-demo"
organization in ThisBuild := "scalaclean"
scalaVersion in ThisBuild := "2.12.8"

// PROJECTS

lazy val parent = project
  .settings(
    name := "parent",
    settings,
    libraryDependencies ++= commonDependencies
  )

lazy val child = project
  .settings(
    name := "child",
    settings,
    libraryDependencies ++= commonDependencies ++ Seq(
      )
  )
  .dependsOn(
    parent
  )

lazy val dependencies =
  new {
    val logbackV      = "1.2.3"
    val scalaLoggingV = "3.7.2"
    val slf4jV        = "1.7.25"
    val scalatestV    = "3.0.4"
    val scalacheckV   = "1.13.5"

    val logback      = "ch.qos.logback"             % "logback-classic" % logbackV
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging"  % scalaLoggingV
    val slf4j        = "org.slf4j"                  % "jcl-over-slf4j"  % slf4jV
    val scalatest    = "org.scalatest"              %% "scalatest"      % scalatestV
    val scalacheck   = "org.scalacheck"             %% "scalacheck"     % scalacheckV
  }

lazy val commonDependencies = Seq(
  dependencies.logback,
  dependencies.scalaLogging,
  dependencies.slf4j,
  dependencies.scalatest  % "test",
  dependencies.scalacheck % "test"
)

// SETTINGS

lazy val settings =
commonSettings ++
scalafmtSettings

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8",
  "-Yrangepos"
)

lazy val commonSettings = Seq(
  addCompilerPlugin("org.scalameta" % "semanticdb-scalac" % "4.1.0" cross CrossVersion.full),
  scalacOptions ++= compilerOptions,
  resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true,
    scalafmtTestOnCompile := true,
    scalafmtVersion := "1.2.0"
  )
