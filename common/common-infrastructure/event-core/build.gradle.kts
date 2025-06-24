plugins {
  id("spring-boot-module")
}

dependencies {
  api(project(":common:common-infrastructure:event-base"))
  compileOnly("org.springframework.boot:spring-boot")
  compileOnly("ch.qos.logback:logback-classic")

  testImplementation(libs.slf4j2.mock)
}
