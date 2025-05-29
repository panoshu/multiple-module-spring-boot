plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":auth-service:api"))
  api(project(":auth-service:domain"))
  api(project(":auth-service:infrastructure"))
}
