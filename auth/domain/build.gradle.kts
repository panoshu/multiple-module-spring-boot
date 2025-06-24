plugins {
  id("java-dependency")
}

dependencies {
  api(project(":common:domain"))
  api(project(":auth:type"))
  implementation(libs.hutool.all)
}
