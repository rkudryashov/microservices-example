package io.microservicessample.apigateway

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig {

    @Bean
    fun additionalRouteLocator(builder: RouteLocatorBuilder): RouteLocator = builder.routes {
        route("path_route") {
            path("/get")
            uri("http://httpbin.org:80")
        }
    }
}
