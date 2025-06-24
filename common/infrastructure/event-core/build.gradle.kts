plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":common:domain"))
  compileOnly("org.springframework.boot:spring-boot")
  compileOnly("ch.qos.logback:logback-classic")
  compileOnly("jakarta.annotation:jakarta.annotation-api")
}
