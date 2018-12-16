allprojects {
    group = "io.microservicesexample"
}

repositories {
    mavenCentral()
}

plugins {
    jacoco
}

tasks.register<JacocoReport>("jacocoRootReport") {
    classDirectories.setFrom(subprojects.map { it.buildDir.resolve("classes") })
    executionData(subprojects.map { it.buildDir.resolve("jacoco/test.exec") })
    reports {
        html.destination = File("$buildDir/jacoco/testCoverageReport")
    }
}
