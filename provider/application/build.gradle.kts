plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":provider:api"))
  api(project(":provider:domain"))
  api(project(":provider:infrastructure"))
}
