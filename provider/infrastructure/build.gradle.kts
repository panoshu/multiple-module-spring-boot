plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":provider:domain"))
  implementation("org.springframework.boot:spring-boot-starter-jooq")
}
