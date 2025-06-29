plugins {
  id("spring-boot-module")
}

dependencies {
  implementation(project(":common:common-infrastructure:event-spring"))
  implementation(project(":auth:domain"))
  implementation("org.springframework.boot:spring-boot-starter-jooq")

  implementation(libs.mapstruct)
  annotationProcessor(libs.mapstruct.processor)
}
