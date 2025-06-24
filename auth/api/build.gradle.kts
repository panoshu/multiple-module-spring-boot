plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":auth:type"))
  implementation(libs.feign.reactor.spring.configuration)
}
