package io.microservicessample.uigateway.config

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig {

    @Bean
    fun routeLocator(builder: RouteLocatorBuilder) = builder.routes {
        route("item-ui-route") {
            path("/items/**")
            uri("lb:http://item-ui")
        }
        // todo what's this?!
        route("hystrix_fallback_route") {
            path("/nonexistent/**")
            filters {
                hystrix { c ->
                    c.apply {
                        name = "hystrixCommand"
                        setFallbackUri("forward:/hystrix-fallback")
                    }
                }
            }
            uri("lb:http://nonexistent-ui")
        }
    }
}
