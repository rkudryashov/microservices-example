package io.microservicesexample.itemsui.config

import io.microservicesexample.itemsui.service.ItemsServiceClient
import io.microservicesexample.itemsui.service.ItemsServiceFeignClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class RoutesConfig {

    @Bean
    fun routes(itemsServiceClient: ItemsServiceClient, itemsServiceFeignClient: ItemsServiceFeignClient) = router {
        GET("/example") {
            val model = mapOf(
                "requestWithRestTemplate" to itemsServiceClient.requestWithRestTemplate(1),
                "requestWithWebClient" to itemsServiceClient.requestWithWebClient(1)
                // todo do we really need Feign with WebFlux?
//                "requestWithFeignClient" to itemsServiceFeignClient.getItem(1)
            )
            ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("example", model)
        }
        GET("/greeting") {
            val usernameHeader = it.headers().header("logged-in-user")
            val username = if (usernameHeader.isEmpty()) "Default" else usernameHeader[0]
            val userRoles = it.headers().header("logged-in-user-roles")
            val model = mapOf("greeting" to "Hi, $username. Your roles are: $userRoles")

            ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("greeting", model)
        }
        GET("/hystrix-fallback") {
            val response = itemsServiceFeignClient.testHystrixFallback()
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(response))
        }
    }
}