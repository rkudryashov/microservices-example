package io.microservicessample.uigateway.config

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig {

    @Bean
    fun additionalRouteLocator(builder: RouteLocatorBuilder) = builder.routes {
        route("greeting_route") {
            path("/greeting/**")
            filters {
                // todo how to apply hystrix to url with exception?
                rewritePath("/greeting/(?<segment>.*)", "/\${segment}")
            }
            // todo rename to ui?
            uri("lb:http://greeting-service")
        }
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
