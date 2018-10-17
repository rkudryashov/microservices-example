package io.microservicesexample.uigateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UiGatewayApplication

fun main(args: Array<String>) {
    runApplication<UiGatewayApplication>(*args)
}
