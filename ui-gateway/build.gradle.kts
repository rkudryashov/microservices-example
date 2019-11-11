import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBootVersion: String by project
val springCloudVersion: String by project
val webjarsBootstrapVersion: String by project
val webjarsLocatorVersion: String by project

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
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-config-client")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.webjars:bootstrap:$webjarsBootstrapVersion")
    implementation("org.webjars:webjars-locator:$webjarsLocatorVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // todo do we need it?
    testImplementation("org.springframework.security:spring-security-test")
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