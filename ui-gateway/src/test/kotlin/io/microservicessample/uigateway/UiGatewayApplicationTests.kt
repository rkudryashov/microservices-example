package io.microservicessample.uigateway

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import java.nio.charset.Charset

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    @Test
    fun shouldReturnLogo() {
        webTestClient.get().uri("/static/images/logo.png")
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.IMAGE_PNG)
    }

    @Test
    @WithMockUser
    fun shouldReturnHystrixFallbackText() {
        webTestClient.get().uri("/nonexistent/")
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8")))
                .expectBody(String::class.java)
                // todo uncomment
                //.isEqualTo<Nothing>("Hello, WebFlux!")
                .returnResult().apply {
                    Assertions.assertEquals(responseBody, "Something went wrong")
                }
    }

    // todo more tests
}
