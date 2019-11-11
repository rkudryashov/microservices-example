repositories {
    mavenCentral()
}

subprojects {
    group = "io.microservicesexample"
    repositories {
        mavenCentral()
        jcenter()
        // todo remove after release
        maven("https://repository.jboss.org/nexus/content/repositories/public/")
    }
}

plugins {
    java
    jacoco
}

tasks {
    val subProjectsClasses = subprojects.map { it.buildDir.resolve("classes/kotlin/main") }
    val subProjectsExecutionData = subprojects.map { it.buildDir.resolve("jacoco/test.exec") }

    register<JacocoReport>("jacocoRootReport") {
        classDirectories.setFrom(subProjectsClasses)
        executionData(subProjectsExecutionData)
        reports {
            html.isEnabled = false
            xml.isEnabled = true
            xml.destination = File("$buildDir/jacoco/report.xml")
        }
    }

    register<JacocoCoverageVerification>("jacocoCoverageVerification") {
        classDirectories.setFrom(subProjectsClasses)
        executionData(subProjectsExecutionData)
        violationRules {
            rule {
                limit {
                    minimum = BigDecimal.valueOf(0.65)
                }
            }
        }
    }
}
