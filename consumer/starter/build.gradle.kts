plugins {
  id("spring-boot-starter")
}

dependencies{
  implementation(project(":consumer:adapter"))

  implementation(libs.feign.reactor)
}
