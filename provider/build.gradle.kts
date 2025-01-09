plugins {
  id("spring-boot-module")
}

dependencies{
  implementation(project(":provider:adapter"))
  runtimeOnly("com.h2database:h2")
  implementation(libs.feign.reactor)
}
