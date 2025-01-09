plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":consumer:api"))
  api(project(":consumer:domain"))
  api(project(":consumer:infrastructure"))
}
