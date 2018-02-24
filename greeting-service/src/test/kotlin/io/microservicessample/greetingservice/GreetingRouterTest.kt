package io.microservicessample.greetingservice

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        // will be fixed in Kotlin 1.3
        // https://jira.spring.io/browse/SPR-15692
        // https://youtrack.jetbrains.com/issue/KT-5464
//                .isEqualTo<Nothing>("Hello, WebFlux!")
    }
}
