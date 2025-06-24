plugins{
  id("spring-boot-module")
  id("org.springframework.boot")
}

dependencies{
  implementation("org.springframework.boot:spring-boot-starter")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

// tasks.withType<Jar> {
//   enabled = false
// }
