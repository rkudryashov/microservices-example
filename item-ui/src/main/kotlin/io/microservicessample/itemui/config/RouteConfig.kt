package io.microservicessample.itemui.config

import io.microservicessample.itemui.service.ItemServiceClient
import io.microservicessample.itemui.service.ItemServiceFeignClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class RouteConfig {

    @Bean
    fun routes(itemServiceClient: ItemServiceClient, itemServiceFeignClient: ItemServiceFeignClient) = router {
        "/items".nest {
            GET("/greeting") {
                val usernameHeader = it.headers().header("logged-in-user")
                val username = if (usernameHeader.isEmpty()) "Default" else usernameHeader[0]
                val model = mapOf("greeting" to username)

                ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("greeting", model)
            }
            // todo more appropriate name
            GET("/example") {
                val model = mapOf(
                        "requestWithRestTemplate" to itemServiceClient.requestWithRestTemplate(1),
                        "requestWithWebClient" to itemServiceClient.requestWithWebClient(1),
                        "requestWithFeignClient" to itemServiceFeignClient.getItem(1)
                )

                ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("example", model)
            }
            GET("/hystrix-fallback") {
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(itemServiceFeignClient.testHystrixFallback()))
            }
        }
    }
}