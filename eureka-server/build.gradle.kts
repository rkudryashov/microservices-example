import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jaxbApiVersion = "2.3.0"
val javaxActivationVersion = "1.1.1"

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${extra["springBootVersion"]}")
        classpath("org.jetbrains.kotlin:kotlin-allopen:${extra["kotlinVersion"]}")
        classpath("org.junit.platform:junit-platform-gradle-plugin:${extra["junitGradlePluginVersion"]}")
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.2.41"
    id("io.spring.dependency-management") version "1.0.5.RELEASE"
}

apply {
    plugin("kotlin-spring")
    plugin("org.springframework.boot")
    plugin("org.junit.platform.gradle.plugin")
}

repositories {
    mavenCentral()
    maven { setUrl("https://repo.spring.io/snapshot") }
    maven { setUrl("https://repo.spring.io/milestone") }
}

dependencies {
    compile("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    compile("org.springframework.cloud:spring-cloud-config-client")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    // need to avoid 'java.lang.TypeNotPresentException: Type javax.xml.bind.JAXBContext not present' if run on jdk 9
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
        mavenBom("org.springframework.cloud:spring-cloud-netflix:${extra["springCloudNetflixVersion"]}")
        mavenBom("org.springframework.cloud:spring-cloud-config:${extra["springCloudConfigVersion"]}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}
