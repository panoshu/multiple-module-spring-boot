plugins {
  id("multi-module-spring-boot-starter")
}

dependencies{
  implementation(project(":provider:adapter"))
  runtimeOnly("com.h2database:h2")
}

subprojects{
  apply(plugin = "multi-module-spring-boot-base")
}
