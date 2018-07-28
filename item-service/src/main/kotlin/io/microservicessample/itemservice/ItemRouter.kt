package io.microservicessample.itemservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router

@Configuration
class ItemRouter {

    @Bean
    fun routes() = router {
        GET("/some-entity") {
            ok().body(BodyInserters.fromObject("some-data"))
        }
    }
}