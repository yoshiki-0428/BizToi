package com.yoshikiohashi.biztoi.configuration

import com.nimbusds.jose.proc.BadJOSEException
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import com.yoshikiohashi.biztoi.model.CognitoAuthenticationToken
import com.yoshikiohashi.biztoi.model.CognitoJWT
import com.yoshikiohashi.biztoi.service.AuthService
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class SecurityContextRepository(
        private val processor: ConfigurableJWTProcessor<com.nimbusds.jose.proc.SecurityContext>,
        private val authService: AuthService
): ServerSecurityContextRepository {

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {
//        try {
            val token = extractToken(exchange.response.headers.getFirst("Authorization"))
            val authentication = extractAuthentication(token, exchange.response.headers.getFirst("RefreshToken"))

            SecurityContextHolder.getContext().authentication = authentication
            return Mono.just(authentication as SecurityContext)
//        } catch (e: AccessDeniedException) {
//            LoggerFactory.getLogger(this.javaClass.simpleName).error("Access denied: ${e.message ?: "No message"}")
//            exchange.response.statusCode = HttpStatus.UNAUTHORIZED
//        }
    }

    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void>? {
        return null
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