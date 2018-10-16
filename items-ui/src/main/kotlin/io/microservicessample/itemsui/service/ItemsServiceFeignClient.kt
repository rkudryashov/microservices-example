package io.microservicessample.itemsui.service

import feign.hystrix.FallbackFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("items-service", fallbackFactory = ItemsServiceFeignClient.ItemsServiceFeignClientFallbackFactory::class)
interface ItemsServiceFeignClient {

    @GetMapping("/items/{id}")
    fun getItem(@PathVariable("id") id: Long): String

    @GetMapping("/not-existing-path")
    fun testHystrixFallback(): String

    @Component
    class ItemsServiceFeignClientFallbackFactory : FallbackFactory<ItemsServiceFeignClient> {
        override fun create(cause: Throwable?) = object : ItemsServiceFeignClient {

            override fun getItem(id: Long) = throw NotImplementedError()

            override fun testHystrixFallback() = "Some error"
        }
    }
}