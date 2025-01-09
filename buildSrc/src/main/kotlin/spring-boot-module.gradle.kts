import org.gradle.accessors.dm.LibrariesForLibs

private val Project.libs: LibrariesForLibs
  get() = extensions.getByType()

plugins{
  id("java-common")
  id("io.spring.dependency-management")
}

dependencyManagement {
  imports {
    mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${libs.versions.spring.cloud.get()}")
  }
}

dependencies {
  compileOnly("org.projectlombok:lombok")
  implementation("org.springframework.boot:spring-boot-starter")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
}
