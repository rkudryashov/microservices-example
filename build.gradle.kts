allprojects {
    group = "io.microservicessample"
    version = "0.0.1-SNAPSHOT"

    val kotlinVersion by extra("1.2.41")
    val springBootVersion by extra("2.0.1.RELEASE")
    val junitGradlePluginVersion by extra("1.1.1")
    val springCloudConfigVersion by extra("2.0.0.RC1")
    val springCloudNetflixVersion by extra("2.0.0.RC1")
}
