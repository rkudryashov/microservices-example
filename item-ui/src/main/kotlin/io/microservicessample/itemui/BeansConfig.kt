package io.microservicessample.itemui

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient

@Configuration
// todo functional registration
class BeansConfig {

    @Bean
    @LoadBalanced
    fun restTemplate() = RestTemplate()

    @Bean
    @LoadBalanced
    fun webClient() = WebClient.builder().build()
}