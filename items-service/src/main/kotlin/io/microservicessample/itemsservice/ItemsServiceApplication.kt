package io.microservicessample.itemsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ItemsServiceApplication

fun main(args: Array<String>) {
    runApplication<ItemsServiceApplication>(*args)
}
