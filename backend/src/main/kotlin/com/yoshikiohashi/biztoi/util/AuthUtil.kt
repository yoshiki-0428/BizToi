package com.yoshikiohashi.biztoi.util

import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import com.yoshikiohashi.biztoi.model.CognitoAuthenticationToken
import com.yoshikiohashi.biztoi.service.AuthService
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class AuthUtil(
        private val processor: ConfigurableJWTProcessor<SecurityContext>
) {
    fun authentication(header: String?): UsernamePasswordAuthenticationToken {
        val token = extractToken(header)
        return if (token == null) {
            UsernamePasswordAuthenticationToken("default", "default", emptyList())
        } else {
            UsernamePasswordAuthenticationToken(
                    extractAuthentication(token)!!.principal, "default",
                    listOf("ROLE_USER")
                            .map { role -> SimpleGrantedAuthority(role) })
        }
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
    fun extractAuthentication(token: String): CognitoAuthenticationToken? =
            try {
                val claims = processor.process(token, null)
                CognitoAuthenticationToken(token, claims)
            } catch (e: Exception) {
                throw AccessDeniedException("${e.javaClass.simpleName} (${e.message ?: "No message"})")
            }
}