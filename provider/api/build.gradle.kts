dependencies {
  api(project(":provider:type"))
  implementation("org.springframework:spring-web")
  implementation("com.playtika.reactivefeign:feign-reactor-spring-configuration:4.2.1")
}
