package com.yoshikiohashi.biztoi.filter

import com.yoshikiohashi.biztoi.util.AuthUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class AuthFilter(
        private val authUtil: AuthUtil
): WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val authentication = authUtil.authentication(exchange.request.headers.getFirst("Authorization"))
        SecurityContextHolder.getContext().authentication = authentication
        return chain.filter(exchange)
    }
}