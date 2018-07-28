package io.microservicessample.itemui

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

import reactor.core.publisher.Mono

@Component
class GreetingHandler {

    fun helloWebflux(request: ServerRequest): Mono<ServerResponse> = ServerResponse
            .ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(BodyInserters.fromObject("Hello, WebFlux!"))

    fun helloException(request: ServerRequest): Mono<ServerResponse> = throw RuntimeException("Some exception...")
}