package io.microservicessample.itemui

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = ["spring.cloud.config.enabled:false"])
class GreetingControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun testGreeting() {
        webTestClient
                .get().uri("/items/greeting")
                .header("logged-in-user", "test")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk
    }
}
