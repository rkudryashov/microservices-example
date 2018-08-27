package io.microservicessample.itemui.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("item-service")
interface ItemServiceFeignClient {

    @GetMapping("/items/{id}")
    fun getItem(@PathVariable("id") id: Long) : String
}