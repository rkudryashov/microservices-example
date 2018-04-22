package io.microservicessample.greetingservice

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = ["spring.cloud.config.enabled:false"])
class GreetingRouterTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun testGreeting() {
        webTestClient
                .get().uri("/greeting")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk
                .expectBody(String::class.java)
                //.isEqualTo<Nothing>("Hello, WebFlux!")
                .returnResult().apply {
                    assertEquals(responseBody, "Hello, WebFlux!")
                }
    }
}
