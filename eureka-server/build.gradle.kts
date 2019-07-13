import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springCloudVersion: String by project
val jaxbApiVersion = "2.3.0"
val javaxActivationVersion = "1.1.1"
val junitVersion: String by project

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    jacoco
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.cloud:spring-cloud-config-client")
    // need to avoid 'java.lang.TypeNotPresentException: Type javax.xml.bind.JAXBContext not present' if run on jdk 11
    implementation("javax.xml.bind:jaxb-api:$jaxbApiVersion")
    implementation("com.sun.xml.bind:jaxb-impl:$jaxbApiVersion")
    implementation("com.sun.xml.bind:jaxb-core:$jaxbApiVersion")
    implementation("javax.activation:activation:$javaxActivationVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}
