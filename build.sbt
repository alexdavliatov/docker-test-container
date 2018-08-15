name := "docker-test-container"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies += "com.dimafeng" %% "testcontainers-scala" % "0.20.1-SNAPSHOT" % "test"
libraryDependencies += "org.testcontainers" % "testcontainers" % "1.8.3"
libraryDependencies += "org.testcontainers" % "postgresql" % "1.8.3"
libraryDependencies += "org.testcontainers" % "kafka" % "1.8.3"
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"


libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.5"
libraryDependencies += "org.scalamock" % "scalamock_2.12" % "4.1.0"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.4"

libraryDependencies += "net.manub" %% "scalatest-embedded-kafka" % "2.0.0" % "test"