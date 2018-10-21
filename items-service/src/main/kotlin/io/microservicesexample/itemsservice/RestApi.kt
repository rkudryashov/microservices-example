package io.microservicesexample.itemsservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class RestApi {

    @Bean
    fun itemsRouter(handler: ItemHandler) = router {
        path("/items").nest {
            GET("/", handler::getAllItems)
            POST("/", handler::addItem)
            GET("/{id}", handler::getItem)
            PUT("/{id}", handler::updateItem)
        }
    }
}