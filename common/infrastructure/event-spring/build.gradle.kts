plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":common:infrastructure:event-core"))
  compileOnly("org.springframework:spring-context")
}
