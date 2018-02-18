package io.microservicessample.apigateway

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {

    @Bean
    fun additionalRouteLocator(builder: RouteLocatorBuilder): RouteLocator = builder.routes {
        route(id = "toGoogle") {
            path("/google")
            filters {
                addResponseHeader("X-TestHeader", "foobar")
            }
            uri("http://meduza.io")
        }
    }
}
