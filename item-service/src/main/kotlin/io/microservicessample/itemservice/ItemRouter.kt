package io.microservicessample.itemservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class ItemRouter {

    @Bean
    fun router(handler: ItemHandler) = router {
        accept(APPLICATION_JSON).and(path("/items")).nest {
            GET("/", handler::getAllItems)
            POST("/", handler::addItem)
            GET("/{id}", handler::getItem)
            PUT("/{id}", handler::updateItem)
        }
    }
}