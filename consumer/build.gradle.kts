plugins {
  id("multi-module-spring-boot-starter")
}

dependencies{
  implementation(project(":consumer:adapter"))
  runtimeOnly("com.h2database:h2")
}

subprojects{
  apply(plugin = "multi-module-spring-boot-base")
}
