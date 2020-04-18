import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
  // Apply the java plugin to add support for Java
  java

  // Apply the application plugin to add support for building a CLI application.
  application

  id("antlr")
}

repositories {
  mavenLocal()
  mavenCentral()
  jcenter()
}

dependencies {
  // Use ANTLR version 4
  antlr("org.antlr:antlr4:4.7.2")

  // Use JUnit Jupiter API for testing.
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")

  // Use JUnit Jupiter Engine for testing.
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_1_8
}

group = "it.maltoni.parsing"
version = "1.0-SNAPSHOT"

application {
  // Define the main class for the application.
  mainClassName = "it.maltoni.parsing.App"
}

tasks {
  val test by getting(Test::class) {
    // Use junit platform for unit tests
    useJUnitPlatform()
    testLogging {
      events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
      showStandardStreams = true
    }
  }

  generateGrammarSource {
    maxHeapSize = "64m"
    arguments = arguments + listOf("-visitor", "-long-messages", "-package", "it.maltoni.parsing")
    outputDirectory = File("$buildDir/generated-src/antlr/main/it/maltoni/parsing/")
  }
}
