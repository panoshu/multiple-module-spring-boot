plugins {
  id("java-dependency")
}

dependencies {
  api(project(":auth-service:type"))
  implementation(libs.hutool.all)
}
