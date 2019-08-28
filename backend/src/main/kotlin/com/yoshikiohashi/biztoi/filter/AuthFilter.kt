package com.yoshikiohashi.biztoi.filter

import com.nimbusds.jose.proc.BadJOSEException
import com.yoshikiohashi.biztoi.model.CognitoAuthenticationToken
import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import com.yoshikiohashi.biztoi.model.CognitoJWT
import com.yoshikiohashi.biztoi.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

/**
 * Filter that handles jwt authentication.
 *
 * @property processor Processor for AWS ID token
 */
@Component
class AuthFilter(
        private val processor: ConfigurableJWTProcessor<SecurityContext>,
        private val authService: AuthService
) : WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        try {
            val token = extractToken(exchange.response.headers.getFirst("Authorization"))
            val authentication = extractAuthentication(token, exchange.response.headers.getFirst("RefreshToken"))

            SecurityContextHolder.getContext().authentication = authentication
            return chain.filter(exchange)
        } catch (e: AccessDeniedException) {
            LoggerFactory.getLogger(this.javaClass.simpleName).error("Access denied: ${e.message ?: "No message"}")
            exchange.response.statusCode = HttpStatus.UNAUTHORIZED
        }
        return chain.filter(exchange)
    }

    /**
     * Extract token from header
     */
    private fun extractToken(header: String?): String? {
        val headers = header?.split("Bearer ")

        return if (headers == null || headers.size < 2) {
            null
        } else {
            headers[1]
        }
    }

    /**
     * Extract authentication details from token
     */
    @Throws(AccessDeniedException::class)
    private fun extractAuthentication(token: String?, refreshToken: String?): CognitoAuthenticationToken? {
        if (token == null)
            return null

        return try {
            val claims = processor.process(token, null)

            CognitoAuthenticationToken(token, claims)
        } catch (be: BadJOSEException) {
            val cognitoJWT: CognitoJWT =
                    authService.refreshToken(refreshToken) ?: throw AccessDeniedException("${be.javaClass.simpleName} (${be.message ?: "No message"})")
            extractAuthentication(cognitoJWT.id_token, cognitoJWT.refresh_token)
        } catch (e: Exception) {
            throw AccessDeniedException("${e.javaClass.simpleName} (${e.message ?: "No message"})")
        }
    }
}