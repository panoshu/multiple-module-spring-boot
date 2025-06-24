plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":provider:type"))
  implementation(libs.feign.reactor.spring.configuration)
}
