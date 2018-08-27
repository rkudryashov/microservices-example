package io.microservicessample.uigateway.misc

import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import java.security.Principal

@Component
class AddCredentialsGlobalFilter : GlobalFilter {

    private val loggedInUserHeader = "logged-in-user"
    private val loggedInUserRolesHeader = "logged-in-user-roles"

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain) = exchange.getPrincipal<Principal>()
            .flatMap {
                val request = exchange.request.mutate()
                        .header(loggedInUserHeader, it.name)
                        .header(loggedInUserRolesHeader, (it as Authentication).authorities?.joinToString(";") ?: "")
                        .build()
                chain.filter(exchange.mutate().request(request).build())
            }
}