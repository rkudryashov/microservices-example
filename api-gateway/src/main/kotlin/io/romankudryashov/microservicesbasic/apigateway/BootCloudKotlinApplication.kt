package io.romankudryashov.microservicesbasic.apigateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BootCloudKotlinApplication

fun main(args: Array<String>) {
    runApplication<BootCloudKotlinApplication>(*args)
}
