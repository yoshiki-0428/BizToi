package com.yoshikiohashi.biztoi.configuration

import com.yoshikiohashi.biztoi.model.CognitoAuthenticationToken
import com.yoshikiohashi.biztoi.util.AuthUtil
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class SecurityContextRepository(private val authUtil: AuthUtil): ServerSecurityContextRepository {

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {
        val authentication: CognitoAuthenticationToken? = authUtil.authentication(exchange.request.headers.getFirst("Authorization"))
        exchange.response.headers.add("BIZ_TOI_TOKEN", authentication!!.token)
        return Mono.just(SecurityContextImpl(authentication))
    }

    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void>? {
        return null
    }
}