package com.yoshikiohashi.biztoi.configuration

import com.yoshikiohashi.biztoi.model.CognitoAuthenticationToken
import com.yoshikiohashi.biztoi.util.AuthUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.net.URI

@Component
class SecurityContextRepository(private val authUtil: AuthUtil): ServerSecurityContextRepository {
    @Value("\${endpoints.authorize}")
    private val authorizeUrl: String = ""

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {
        if (exchange.request.path.value() == "/api/auth/token" || exchange.request.method == HttpMethod.OPTIONS) {
            return Mono.just(SecurityContextImpl(null))
        }
        val authentication: CognitoAuthenticationToken? = authUtil.authentication(exchange.request.headers.getFirst("Authorization"))
        if (authentication == null) {
            exchange.response.statusCode = HttpStatus.PERMANENT_REDIRECT
            exchange.response.headers.location = URI(authorizeUrl)
            return Mono.empty()
        }
        return Mono.just(SecurityContextImpl(authentication))
    }

    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void>? {
        return null
    }
}