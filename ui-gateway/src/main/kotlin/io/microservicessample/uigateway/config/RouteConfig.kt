package io.microservicessample.uigateway.config

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class RouteConfig {

    @Bean
    fun routeLocator(builder: RouteLocatorBuilder) = builder.routes {
        route("item-ui-route") {
            path("/items/**")
            uri("lb:http://item-ui")
        }
    }

    @Bean
    fun routes() = router {
        GET("/login") { ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("login") }
    }
}
