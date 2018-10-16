package io.microservicessample.itemsui.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class ItemsServiceClient(
        private val restTemplate: RestTemplate,
        private val webClient: WebClient
) {

    fun requestWithRestTemplate(id: Long): String =
            restTemplate.getForEntity("http://items-service/items/$id", String::class.java).body ?: "No result"

    fun requestWithWebClient(id: Long): Mono<String> =
            webClient.get().uri("http://items-service/items/${id}").retrieve().bodyToMono(String::class.java)
}
