package io.microservicessample.itemsservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class Config {

    @Autowired
    private lateinit var itemRepository: ItemRepository

    @Bean
    fun router(handler: ItemHandler) = router {
        accept(APPLICATION_JSON).and(path("/items")).nest {
            GET("/", handler::getAllItems)
            POST("/", handler::addItem)
            GET("/{id}", handler::getItem)
            PUT("/{id}", handler::updateItem)
        }
    }

    @Bean
    fun dataInitializer() = CommandLineRunner {
        val first = Item(null, "first")
        val second = Item(null, "second")
        itemRepository.save(first)
        itemRepository.save(second)
    }
}