package io.microservicesexample.itemsui

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = [
        "spring.cloud.config.enabled:false",
        "feign.hystrix.enabled:true"
    ]
)
class RouterTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun testGreeting() {
        webTestClient
            .get().uri("/greeting")
            .header("logged-in-user", "test")
            .accept(MediaType.TEXT_PLAIN)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun testHystrixFallback() {
        webTestClient
            .get().uri("/hystrix-fallback")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(String::class.java)
            .returnResult().apply {
                MatcherAssert.assertThat(this.responseBody, Matchers.equalTo("{\"error\" : \"Some error\"}"))
            }
    }
}
