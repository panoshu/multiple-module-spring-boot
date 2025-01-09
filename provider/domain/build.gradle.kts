plugins {
  id("java-dependency")
}

dependencies {
  api(project(":provider:type"))
  implementation(libs.hutool.all)
}
