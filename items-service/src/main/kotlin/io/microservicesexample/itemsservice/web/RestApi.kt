package io.microservicesexample.itemsservice.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class RestApi {

    @Bean
    fun itemsRouter(handler: ItemHandler) = router {
        path("/items").nest {
            GET("/", handler::getAll)
            POST("/", handler::add)
            GET("/{id}", handler::getOne)
            PUT("/{id}", handler::update)
        }
    }
}