plugins {
  `kotlin-dsl`
}

repositories {
  mavenLocal()
  gradlePluginPortal()
}

dependencies {
  // implementation("org.springframework.boot:org.springframework.boot.gradle.plugin:3.4.1")
  // implementation("io.spring.dependency-management:io.spring.dependency-management.gradle.plugin:1.1.7")
  implementation(libs.spring.boot)
  implementation(libs.dependency.management)
  implementation(libs.spring.cloud)
}
