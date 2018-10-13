allprojects {
    group = "io.microservicessample"

    val kotlinVersion by extra("1.2.70")
    // todo update with subProjects
    val springBootVersion by extra("2.0.5.RELEASE")
    val springCloudDependenciesVersion by extra("Finchley.SR1")
}
