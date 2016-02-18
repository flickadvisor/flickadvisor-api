name := """rest-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  javaJpa,
  filters,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final",
  "org.projectlombok" % "lombok-maven" % "1.16.6.1",
  "org.postgresql" % "postgresql" % "9.4.1207.jre7",
  "org.springframework.data" % "spring-data-jpa" % "1.9.2.RELEASE",
  "org.mindrot" % "jbcrypt" % "0.3m"

)     

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
