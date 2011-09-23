import sbt._
import Keys._
import sbtprotobuf._
import ProtobufPlugin._
import ScalaBuffPlugin._

object build extends Build {

  lazy val root = Project(
    id = "sbt-protobuf-test02",
    base = file("."),
    settings = Defaults.defaultSettings ++ protobufSettings ++ scalaBuffSettings ++ Seq[Setting[_]](
      organization := "com.gerolfseitz",

      name := "sbt-protobuf-test02",

      version := "0.0.2",

      resolvers += "gseitz@github" at "http://gseitz.github.com/maven/",

      publishArtifact in packageDoc := false,

      scalaVersion := "2.9.1",

      // uncomment the following line to include the *.proto files in the packaged jar
      // unmanagedResourceDirectories in Compile <+= (sourceDirectory in scalaBuffConfig).identity,

      // set the directory for generated scala sources to src/main/generated_scala
      generatedSource in scalaBuffConfig <<= (sourceDirectory in Compile)(_ / "generated_scala"),
      generatedSource in protobufConfig <<= (sourceDirectory in Compile)(_ / "generated_java"),

      // it's not possible to generate both java and scala sources due to a "bug" in ScalaBuff.
      addProtocCompatibility
    )
  )

}
