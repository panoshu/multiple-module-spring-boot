plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":auth:api"))
  api(project(":auth:domain"))
  api(project(":auth:infrastructure"))
}
