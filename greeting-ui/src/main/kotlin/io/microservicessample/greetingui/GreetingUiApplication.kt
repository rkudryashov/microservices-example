package io.microservicessample.greetingui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GreetingUiApplication

fun main(args: Array<String>) {
    runApplication<GreetingUiApplication>(*args)
}
