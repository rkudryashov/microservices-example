rootProject.name = "microservices-sample"

include("ui-gateway", "eureka-server", "config-server", "items-ui", "items-service")

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.plugin.spring" -> {
                    val kotlinVersion: String by settings
                    useVersion(kotlinVersion)
                }
                "org.jetbrains.kotlin.jvm" -> {
                    val kotlinVersion: String by settings
                    useVersion(kotlinVersion)
                }
                "org.springframework.boot" -> {
                    val springBootPluginVersion: String by settings
                    useVersion(springBootPluginVersion)
                }
                "io.spring.dependency-management" -> {
                    val springDependencyManagementPluginVersion: String by settings
                    useVersion(springDependencyManagementPluginVersion)
                }
            }
        }
    }
}