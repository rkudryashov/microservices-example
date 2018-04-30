package io.microservicessample.greetingservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class GreetingRouter {

    @Bean
    fun route(greetingHandler: GreetingHandler) = RouterFunctions.route(RequestPredicates.GET("/webflux")
            .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
            HandlerFunction<ServerResponse> { greetingHandler.hello(it) })
}