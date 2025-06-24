plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":consumer:domain"))
  runtimeOnly("com.h2database:h2")
  implementation("org.springframework.boot:spring-boot-starter-jooq")
}
