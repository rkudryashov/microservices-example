package io.microservicessample.uigateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
// todo enable hystrix dashboard
class UiGatewayApplication

fun main(args: Array<String>) {
    runApplication<UiGatewayApplication>(*args)
}
