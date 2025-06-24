plugins {
  id("spring-boot-starter")
}

dependencies{
  implementation(project(":provider:adapter"))
  implementation(libs.feign.reactor)
}
