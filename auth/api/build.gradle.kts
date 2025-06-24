plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":auth:type"))
  // implementation("org.springframework:spring-web")
  implementation(libs.feign.reactor.spring.configuration)
}
