organization in ThisBuild := "com.noel"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

lazy val `reservation` = (project in file("."))
  .aggregate(`reservation-api`, `reservation-impl`)

lazy val `reservation-api` = (project in file("reservation-api"))
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      lombok
    )
  )

lazy val `reservation-impl` = (project in file("reservation-impl"))
  .enablePlugins(LagomJava)
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslPersistenceCassandra,
      lagomJavadslKafkaBroker,
      lagomJavadslTestKit,
      lombok
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`reservation-api`)
val lombok = "org.projectlombok" % "lombok" % "1.16.16"

def common = Seq(
  javacOptions in compile += "-parameters"
)

