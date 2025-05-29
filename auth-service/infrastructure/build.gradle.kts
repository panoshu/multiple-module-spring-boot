plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":auth-service:domain"))
  implementation("org.springframework.boot:spring-boot-starter-jooq")
}
