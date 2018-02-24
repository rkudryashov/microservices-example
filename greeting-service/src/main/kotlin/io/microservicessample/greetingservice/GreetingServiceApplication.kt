package io.microservicessample.greetingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class GreetingServiceApplication

fun main(args: Array<String>) {
    runApplication<GreetingServiceApplication>(*args)
}
