package io.microservicessample.apigateway

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiGatewayApplicationTests {

    @LocalServerPort
    private var port: Int? = null
    private lateinit var client: WebTestClient

    @BeforeEach
    fun setup() {
        client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build()
    }

    @Test
    fun pathRouteWorks() {
        client.get().uri("/get")
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
        client.get().uri("/anything/123")
                .exchange()
                .expectStatus().isUnauthorized
                .expectBody(Map::class.java)
        // todo enable
//                .consumeWith<Nothing> {
//                    assertThat(it.responseBody).isNotEmpty
//                }
    }
}
