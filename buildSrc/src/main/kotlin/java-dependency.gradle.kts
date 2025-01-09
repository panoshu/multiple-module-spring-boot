import org.gradle.accessors.dm.LibrariesForLibs

private val Project.libs: LibrariesForLibs
  get() = extensions.getByType()

plugins {
  id("java-common")
}

dependencies {
  compileOnly(libs.lombok)

  testCompileOnly(libs.lombok)
  testImplementation(libs.junit.jupiter)
  testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
  useJUnitPlatform()
}
