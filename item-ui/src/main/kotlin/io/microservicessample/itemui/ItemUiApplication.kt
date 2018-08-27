package io.microservicessample.itemui

import io.microservicessample.itemui.service.ItemServiceFeignClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients(clients = [ItemServiceFeignClient::class])
class ItemUiApplication

fun main(args: Array<String>) {
    runApplication<ItemUiApplication>(*args)
}
