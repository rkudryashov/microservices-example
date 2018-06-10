package io.microservicessample.greetingui

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class GreetingRouter {

    @Bean
    fun routes(greetingHandler: GreetingHandler) = router {
        GET("/webflux", greetingHandler::helloWebflux)
        GET("/exception", greetingHandler::helloException)
    }
}