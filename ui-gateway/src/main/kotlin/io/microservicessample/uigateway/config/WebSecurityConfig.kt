package io.microservicessample.uigateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
class WebSecurityConfig {

    @Bean
    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
                .formLogin().loginPage("/login")
                .and()
                .authorizeExchange()
                .pathMatchers("/login").permitAll()
                .pathMatchers("/static/**").permitAll()
                .pathMatchers("/greeting/**").authenticated()
                .anyExchange().denyAll()
                .and()
                .csrf().disable()
                .build()
    }

    @Bean
    fun reactiveUserDetailsService(): ReactiveUserDetailsService {
        val user = User.withDefaultPasswordEncoder()
                .username("john_doe").password("qwerty").roles("USER").build()
        return MapReactiveUserDetailsService(user)
    }
}