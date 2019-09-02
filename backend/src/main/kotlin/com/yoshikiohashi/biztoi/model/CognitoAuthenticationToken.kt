package com.yoshikiohashi.biztoi.model

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/**
 * AWS identity token for security context
 *
 * @property token raw token
 */
class CognitoAuthenticationToken(
        private val token: String,
        details: TokenClaims,
        authorities: List<GrantedAuthority> = listOf()
) : UsernamePasswordAuthenticationToken(details, authorities) {
    init {
        setDetails(details)
    }

    override fun getCredentials(): Any {
        return token
    }

    override fun getPrincipal(): Any {
        return details
    }
}