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
    classDirectories.setFrom(subprojects.map { it.buildDir.resolve("classes/kotlin/main") })
    executionData(subprojects.map { it.buildDir.resolve("jacoco/test.exec") })
    reports {
        html.isEnabled = false
        xml.isEnabled = true
        xml.destination = File("$buildDir/jacoco/report.xml")
    }
}
