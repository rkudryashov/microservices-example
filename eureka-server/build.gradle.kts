import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBootVersion: String by project
val springCloudVersion: String by project

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    jacoco
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(enforcedPlatform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    implementation(enforcedPlatform("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion"))
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.cloud:spring-cloud-config-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "12"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}