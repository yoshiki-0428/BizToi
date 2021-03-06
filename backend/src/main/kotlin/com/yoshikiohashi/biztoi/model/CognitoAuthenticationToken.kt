package com.yoshikiohashi.biztoi.model

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/**
 * AWS identity token for security context
 *
 * @property token raw token
 */
class CognitoAuthenticationToken(
        val token: String,
        details: TokenClaims,
        authorities: List<GrantedAuthority> = listOf()
) : AbstractAuthenticationToken(authorities) {
    init {
        setDetails(details)
        isAuthenticated = true
    }

    override fun getCredentials(): Any {
        return token
    }

    override fun getPrincipal(): Any {
        return details
    }
}