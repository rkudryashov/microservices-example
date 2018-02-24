package io.microservicessample.apigateway

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiGatewayApplicationTests {

    // Spring Boot will create a `WebTestClient` for you,
    // already configure and ready to issue requests against "localhost:RANDOM_PORT"
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun pathRouteWorks() {
        webTestClient.get().uri("/get")
                .exchange()
                .expectStatus().isOk
                .expectBody(Map::class.java)
        // todo enable
//                .consumeWith<Nothing> {
//                    assertThat(it.responseBody).isNotEmpty
//                }
    }

    @Test
    fun shouldReturnUnauthorizedHttpStatus() {
        webTestClient.get().uri("/anything/123")
                .exchange()
                .expectStatus().isUnauthorized
                .expectBody(Map::class.java)
        // todo enable
//                .consumeWith<Nothing> {
//                    assertThat(it.responseBody).isNotEmpty
//                }
    }
}
