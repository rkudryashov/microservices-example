package io.microservicesexample.itemsui.config

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class BeansConfig {

    @Bean
    @LoadBalanced
    fun restTemplate() = RestTemplate()

    @Bean
    fun webClient(loadBalancerClient: LoadBalancerClient) = WebClient.builder()
            .filter(LoadBalancerExchangeFilterFunction(loadBalancerClient))
            .build()
}