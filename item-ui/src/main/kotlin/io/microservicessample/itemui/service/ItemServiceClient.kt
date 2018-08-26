package io.microservicessample.itemui.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class ItemServiceClient(
        private val restTemplate: RestTemplate,
        private val webClient: WebClient
) {

    fun requestWithRestTemplate(): String =
            restTemplate.getForEntity("http://item-service/items/1", String::class.java).body ?: "No result"

    fun requestWithWebClient(): Mono<String> =
            webClient.get().uri("http://item-service/items/2").retrieve().bodyToMono(String::class.java)
}
