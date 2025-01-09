plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":consumer:application"))
  api("org.springframework.boot:spring-boot-starter-web")
  api("org.springframework.boot:spring-boot-starter-webflux")
  implementation(project(":provider:api"))
  implementation(libs.hutool.all)
}
