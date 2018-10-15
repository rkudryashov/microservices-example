package io.microservicessample.uigateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class RouteConfig {

    @Bean
    fun routes() = router {
        GET("/login") { ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("login") }
    }
}
