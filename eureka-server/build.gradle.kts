import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springCloudVersion: String by project
val jaxbApiVersion = "2.3.0"
val javaxActivationVersion = "1.1.1"

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    compile("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    compile("org.springframework.cloud:spring-cloud-config-client")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    // need to avoid 'java.lang.TypeNotPresentException: Type javax.xml.bind.JAXBContext not present' if run on jdk 11
    compile("javax.xml.bind:jaxb-api:$jaxbApiVersion")
    compile("com.sun.xml.bind:jaxb-impl:$jaxbApiVersion")
    compile("com.sun.xml.bind:jaxb-core:$jaxbApiVersion")
    compile("javax.activation:activation:$javaxActivationVersion")
    testCompile("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testCompile("org.junit.jupiter:junit-jupiter-api")
    testRuntime("org.junit.jupiter:junit-jupiter-engine")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks {
    tasks.withType<Test> {
        useJUnitPlatform()
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}
