package io.microservicesexample.itemsservice

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBodyList
import org.springframework.web.reactive.function.BodyInserters

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = ["spring.cloud.config.enabled:false"])
class RestApiTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun testGetAllItems() {
        webTestClient
                .get().uri("/items/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList<Item>()
                .hasSize(2)
                .returnResult().apply {
                    assertThat(this.responseBody, containsInAnyOrder(
                            Item(1, "first"),
                            Item(2, "second")
                    ))
                }
    }

    @Test
    fun testCreateItem() {
        webTestClient
                .post().uri("/items/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(Item(null, "third")))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Item::class.java)
                .returnResult().apply {
                    assertThat(this.responseBody, equalTo(Item(3, "third")))
                }
    }

    @Test
    fun testGetOneItems() {
        webTestClient
                .get().uri("/items/2")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Item::class.java)
                .returnResult().apply {
                    assertThat(this.responseBody, equalTo(Item(2, "second")))
                }
    }

    @Test
    @DirtiesContext
    fun testUpdateItem() {
        webTestClient
                .put().uri("/items/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(Item(2, "more than 2")))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Item::class.java)
                .returnResult().apply {
                    assertThat(this.responseBody, equalTo(Item(2, "more than 2")))
                }
    }
}