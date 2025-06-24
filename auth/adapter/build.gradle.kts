plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":auth:application"))
  api("org.springframework.boot:spring-boot-starter-web")
  api("org.springframework.boot:spring-boot-starter-webflux")
}
