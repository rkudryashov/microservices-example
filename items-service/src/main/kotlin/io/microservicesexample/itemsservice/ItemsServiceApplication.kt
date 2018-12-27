package io.microservicesexample.itemsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ItemsServiceApplication

// todo create package structure
fun main(args: Array<String>) {
    runApplication<ItemsServiceApplication>(*args)
}
