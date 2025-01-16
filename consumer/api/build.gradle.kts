plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":consumer:type"))
  implementation(libs.feign.reactor.spring.configuration)
}
