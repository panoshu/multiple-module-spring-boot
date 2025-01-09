plugins {
  id("java-dependency")
}

dependencies {
  api(project(":consumer:type"))
  implementation(libs.hutool.all)
}
