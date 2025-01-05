rootProject.name = "multiple-module-spring-boot"

include("provider")
include("provider:type")
include("provider:api")
include("provider:application")
include("provider:domain")
include("provider:infrastructure")
include("provider:adapter")

include("consumer")
include("consumer:type")
include("consumer:api")
include("consumer:application")
include("consumer:domain")
include("consumer:infrastructure")
include("consumer:adapter")
