plugins {
  id("spring-boot-module")
}

dependencies {
  implementation(project(":common:common-infrastructure:event-spring"))
  api(project(":auth:domain"))
  implementation("org.springframework.boot:spring-boot-starter-jooq")
}
