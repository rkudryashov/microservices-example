rootProject.name = "microservices-example"

include(
    "ui-gateway",
    "eureka-server",
    "config-server",
    "items-ui",
    "items-service"
)

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
            }
        }
    }
}