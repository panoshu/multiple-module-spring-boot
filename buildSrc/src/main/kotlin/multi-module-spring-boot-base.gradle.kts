plugins{
  id("multi-module-java-base")
  id("io.spring.dependency-management")
}

dependencyManagement {
  imports {
    mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
  }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}
