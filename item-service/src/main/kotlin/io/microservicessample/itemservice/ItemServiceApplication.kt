package io.microservicessample.itemservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ItemServiceApplication

fun main(args: Array<String>) {
    runApplication<ItemServiceApplication>(*args)
}
