package io.microservicesexample.itemsui.service

import feign.hystrix.FallbackFactory
import io.microservicesexample.itemsui.ItemsUiException
import org.slf4j.LoggerFactory
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

        private val log = LoggerFactory.getLogger(this::class.java)

        override fun create(cause: Throwable) = object : ItemsServiceFeignClient {

            override fun getItem(id: Long): String {
                log.error("Cannot get item with id=$id")
                throw ItemsUiException(cause)
            }

            override fun testHystrixFallback(): String {
                log.error("This is expected error")
                return "Some error"
            }
        }
    }
}