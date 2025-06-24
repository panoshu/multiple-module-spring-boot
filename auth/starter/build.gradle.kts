plugins {
  id("spring-boot-starter")
}

dependencies{
  implementation(project(":auth:adapter"))
  implementation(libs.feign.reactor)
}
