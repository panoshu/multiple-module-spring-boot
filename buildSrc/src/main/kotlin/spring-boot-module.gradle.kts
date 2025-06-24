import org.gradle.accessors.dm.LibrariesForLibs

private val Project.libs: LibrariesForLibs
  get() = extensions.getByType()

plugins{
  id("java-common")
  id("custom-naming")
  id("io.spring.dependency-management")
}

dependencyManagement {
  imports {
    mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
  }
}

val mockitoAgent: Configuration by configurations.creating {
  isTransitive = false
}

dependencies {
  implementation(platform(libs.spring.cloud.dependency))

  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")

  testCompileOnly(libs.lombok)
  testAnnotationProcessor(libs.lombok)
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testRuntimeOnly(libs.junit.platform.launcher)

  mockitoAgent(libs.mockito.core)
}

tasks.withType<Test> {
  jvmArgs("-javaagent:${mockitoAgent.asPath}")
}
