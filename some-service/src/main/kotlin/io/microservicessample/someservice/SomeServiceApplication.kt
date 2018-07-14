package io.microservicessample.someservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SomeServiceApplication

fun main(args: Array<String>) {
    runApplication<SomeServiceApplication>(*args)
}
