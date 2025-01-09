plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":consumer:domain"))
  implementation("org.springframework.boot:spring-boot-starter-jooq")
}
