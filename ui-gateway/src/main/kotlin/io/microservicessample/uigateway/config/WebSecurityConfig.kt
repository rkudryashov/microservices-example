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
    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain = http
            .formLogin().loginPage("/login")
            .and()
            .authorizeExchange()
            .pathMatchers("/login").permitAll()
            .pathMatchers("/static/**").permitAll()
            .pathMatchers("/favicon.ico").permitAll()
            .pathMatchers("/webjars/**").permitAll()
            .pathMatchers("/actuator/**").hasRole("ADMIN")
            .anyExchange().authenticated()
            .and()
            .csrf().disable()
            .build()

    @Bean
    fun reactiveUserDetailsService(): ReactiveUserDetailsService {
        // todo remove deprecated method
        val user = User.withDefaultPasswordEncoder()
                .username("john_doe").password("qwerty").roles("USER", "ADMIN")
                .build()
        val admin = User.withDefaultPasswordEncoder()
                .username("admin").password("admin").roles("ADMIN")
                .build()
        return MapReactiveUserDetailsService(user, admin)
    }
}