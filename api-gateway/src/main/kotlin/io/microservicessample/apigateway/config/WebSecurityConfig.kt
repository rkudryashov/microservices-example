package io.microservicessample.apigateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
class WebSecurityConfig {

    @Bean
    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.httpBasic().and()
                .authorizeExchange()
                .pathMatchers("/anything/**").authenticated()
                .anyExchange().permitAll()
                .and()
                .build()
    }

    @Bean
    fun reactiveUserDetailsService(): MapReactiveUserDetailsService {
        val user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build()
        return MapReactiveUserDetailsService(user)
    }
}