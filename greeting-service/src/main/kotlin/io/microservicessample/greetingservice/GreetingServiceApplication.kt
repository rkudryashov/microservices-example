package io.microservicessample.greetingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GreetingServiceApplication

fun main(args: Array<String>) {
    runApplication<GreetingServiceApplication>(*args)
}
