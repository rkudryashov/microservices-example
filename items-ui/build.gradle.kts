import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springCloudVersion: String by project
val webjarsBootstrapVersion: String by project
val webjarsLocatorVersion: String by project

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
    compile("org.springframework.boot:spring-boot-starter-webflux")
    compile("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    compile("org.springframework.cloud:spring-cloud-config-client")
    compile("org.springframework.cloud:spring-cloud-starter-openfeign")
    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    compile("org.webjars:bootstrap:$webjarsBootstrapVersion")
    compile("org.webjars:webjars-locator:$webjarsLocatorVersion")

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
