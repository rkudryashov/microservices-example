package io.microservicessample.uigateway

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = ["spring.cloud.config.enabled:false"])
class UiGatewayApplicationTests {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun shouldReturnUnauthorizedHttpStatus() {
        webTestClient.get().uri("/anything/123")
                .exchange()
                .expectStatus().is3xxRedirection
                .expectHeader().valueEquals("Location", "/login")
                .expectBody().isEmpty
    }

    // todo more tests
}
