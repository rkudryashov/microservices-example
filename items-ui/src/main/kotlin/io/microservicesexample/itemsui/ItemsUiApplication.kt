package io.microservicesexample.itemsui

import io.microservicesexample.itemsui.service.ItemsServiceFeignClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients(clients = [ItemsServiceFeignClient::class])
class ItemsUiApplication

fun main(args: Array<String>) {
    runApplication<ItemsUiApplication>(*args)
}
