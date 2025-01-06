plugins {
  id("multi-module-spring-boot-starter")
}

dependencies{
  implementation(project(":provider:adapter"))
  runtimeOnly("com.h2database:h2")
  implementation(libs.feign.reactor)
}

subprojects{
  apply(plugin = "multi-module-spring-boot-base")
}
