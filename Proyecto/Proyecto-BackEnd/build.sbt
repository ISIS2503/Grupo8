import play.ebean.sbt.PlayEbean
import play.routes.compiler.InjectedRoutesGenerator
import play.sbt.PlayJava

name := """proyecto"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "junit" % "junit" % "4.12" % "test",
  "org.mockito" % "mockito-core" % "2.10.0" % "test",
  "com.datastax.cassandra" % "cassandra-driver-core" % "2.0.1",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.9.Final",
  filters
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
