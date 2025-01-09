import org.gradle.accessors.dm.LibrariesForLibs

plugins {
  `java-library`
  idea
}

group = "com.example"
version = "0.0.1"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenLocal()
  maven("https://maven.aliyun.com/repository/public")
  maven("https://maven.aliyun.com/repository/central")
  mavenCentral()
}

dependencies {
  compileOnly(libs.lombok)
  annotationProcessor(libs.lombok)
  // testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

private val Project.libs: LibrariesForLibs
  get() = extensions.getByType()
