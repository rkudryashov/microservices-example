package io.microservicessample.apigateway.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod

@Configuration
class RouteConfig {

    @Bean
    fun additionalRouteLocator(builder: RouteLocatorBuilder): RouteLocator = builder.routes {
        route("path_route") {
            path("/get")
            uri("http://httpbin.org:80")
        }
        route("greeting_route") {
            path("/greeting")
            method(HttpMethod.GET)
            uri("lb://greeting-service")
        }
    }
}
