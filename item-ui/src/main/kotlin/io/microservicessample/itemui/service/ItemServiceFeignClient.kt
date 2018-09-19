package io.microservicessample.itemui.service

import feign.hystrix.FallbackFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("item-service", fallbackFactory = ItemServiceFeignClient.ItemServiceFeignClientFallbackFactory::class)
interface ItemServiceFeignClient {

    @GetMapping("/items/{id}")
    fun getItem(@PathVariable("id") id: Long): String

    @GetMapping("/not-existing-path")
    fun testHystrixFallback(): String

    @Component
    class ItemServiceFeignClientFallbackFactory : FallbackFactory<ItemServiceFeignClient> {
        override fun create(cause: Throwable?) = object : ItemServiceFeignClient {

            override fun getItem(id: Long) = throw NotImplementedError()

            override fun testHystrixFallback() = "Some error"
        }
    }
}