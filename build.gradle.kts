allprojects {
    group = "io.microservicessample"
    version = "0.0.1-SNAPSHOT"

    val kotlinVersion by extra("1.2.41")
    val springBootVersion by extra("2.0.1.RELEASE")
    val springCloudDependenciesVersion by extra("Finchley.RC1")
    val junitGradlePluginVersion by extra("1.1.1")
}
