name := "camelcode"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.apache.camel" % "camel-core" % "2.10.7",
    "org.apache.camel" % "camel-csv" % "2.10.7",
    "org.apache.camel" % "camel-bindy" % "2.10.7",
    "org.apache.camel" % "camel-jackson" % "2.10.7",
    "org.apache.camel" % "camel-http" % "2.10.7",
    "org.geotools" % "gt-main" % "10.0" excludeAll ExclusionRule(organization = "javax.media"),
    "org.geotools" % "gt-epsg-hsql" % "10.0" excludeAll ExclusionRule(organization = "javax.media"),
	"org.reflections" % "reflections" % "0.9.9-RC1",
	"com.yammer.metrics" % "metrics-core" % "2.2.0",
	"com.google.inject" % "guice" % "3.0",
    "com.google.inject.extensions" % "guice-assistedinject" % "3.0",
    "com.google.inject.extensions" % "guice-multibindings" % "3.0",
    "com.google.inject.extensions" % "guice-throwingproviders" % "3.0",
    "org.mongodb" % "mongo-java-driver" % "2.11.3",
    "org.mongodb.morphia" % "morphia" % "0.105",
	"org.mongodb.morphia" % "morphia-validation" % "0.105",
    "org.mongodb.morphia" % "morphia-logging-slf4j" % "0.105",
	"org.webjars" %% "webjars-play" % "2.2.1" exclude("org.scala-lang", "scala-library"),
	"org.webjars" % "bootstrap" % "3.0.2",
	"com.google.guava" % "guava" % "15.0"            
	)     

  resolvers += "Morphia repository" at "http://morphia.googlecode.com/svn/mavenrepo/"
  
  resolvers += "Open Source Geospatial Foundation Repository" at "http://download.osgeo.org/webdav/geotools/"
   
  resolvers += "OpenGeo Maven Repository" at "http://repo.opengeo.org"

play.Project.playJavaSettings
