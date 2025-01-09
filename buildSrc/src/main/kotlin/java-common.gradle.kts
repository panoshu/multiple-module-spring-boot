import org.gradle.accessors.dm.LibrariesForLibs

private val Project.libs: LibrariesForLibs
  get() = extensions.getByType()

plugins {
  `java-library`
  idea
}

group = "com.example"
version = "0.0.1"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(libs.versions.javaLanguageVersion.get())
  }
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
  testCompileOnly {
    extendsFrom(configurations.testAnnotationProcessor.get())
  }
}

repositories {
  mavenLocal()
  maven("https://maven.aliyun.com/repository/public")
  maven("https://maven.aliyun.com/repository/central")
  mavenCentral()
}

tasks.withType<Test> {
  useJUnitPlatform()
}
