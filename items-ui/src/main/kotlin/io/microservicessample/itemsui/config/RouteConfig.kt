package io.microservicessample.itemsui.config

import io.microservicessample.itemsui.service.ItemsServiceClient
import io.microservicessample.itemsui.service.ItemsServiceFeignClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class RouteConfig {

    @Bean
    fun routes(itemsServiceClient: ItemsServiceClient, itemsServiceFeignClient: ItemsServiceFeignClient) = router {
        GET("/example") {
            val model = mapOf(
                    "requestWithRestTemplate" to itemsServiceClient.requestWithRestTemplate(1),
                    "requestWithWebClient" to itemsServiceClient.requestWithWebClient(1),
                    "requestWithFeignClient" to itemsServiceFeignClient.getItem(1)
            )
            ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("example", model)
        }
        GET("/greeting") {
            val usernameHeader = it.headers().header("logged-in-user")
            val username = if (usernameHeader.isEmpty()) "Default" else usernameHeader[0]
            val model = mapOf("greeting" to username)

            ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("greeting", model)
        }
        GET("/hystrix-fallback") {
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(itemsServiceFeignClient.testHystrixFallback()))
        }
    }
}