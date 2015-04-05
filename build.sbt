name := """hello-play-2_3-scala"""

version := "1.0-SNAPSHOT"

//"Kamon Repository" at "http://repo.kamon.io"

//val kamonVersion = "0.3.5"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.3-M1",
  "org.webjars" % "bootstrap" % "2.3.1",
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
//  "io.kamon" %% "kamon-core_2.11" % kamonVersion,
//  "io.kamon" %% "kamon-statsd_2.11" % kamonVersion,
//  "io.kamon" %% "kamon-log-reporter_2.11" % kamonVersion,
//  "io.kamon" %% "kamon-system-metrics_2.11" % kamonVersion
//  "org.aspectj" % "aspectjweaver" % "1.8.1"
)

lazy val root = (project in file(".")).addPlugins(PlayScala)

//aspectjSettings

//javaOptions <++= AspectjKeys.weaverOptions in Aspectj

// when you call "sbt run" aspectj weaving kicks in
//fork in run := true
