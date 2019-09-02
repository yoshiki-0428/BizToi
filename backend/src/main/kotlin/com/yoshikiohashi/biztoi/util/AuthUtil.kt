package com.yoshikiohashi.biztoi.util

import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import com.yoshikiohashi.biztoi.model.CognitoAuthenticationToken
import com.yoshikiohashi.biztoi.model.TokenClaims
import com.yoshikiohashi.biztoi.service.AuthService
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class AuthUtil(
        private val processor: ConfigurableJWTProcessor<SecurityContext>
) {
    fun authentication(header: String?): CognitoAuthenticationToken? {
        val token = extractToken(header)
        return if (token == null) {
            CognitoAuthenticationToken("default", TokenClaims(), emptyList())
        } else {
            extractAuthentication(token)
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
                val claims: JWTClaimsSet = processor.process(token, null)
                CognitoAuthenticationToken(
                        token, TokenClaims(claims),
                        listOf("ROLE_USER").map { role -> SimpleGrantedAuthority(role) })
            } catch (e: Exception) {
                CognitoAuthenticationToken("default", TokenClaims(), emptyList())
            }
}