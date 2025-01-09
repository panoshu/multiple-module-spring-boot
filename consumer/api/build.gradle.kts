plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":consumer:type"))
  // implementation("org.springframework:spring-web")
  implementation(libs.feign.reactor.spring.configuration)
}
