package com.yoshikiohashi.biztoi.model

import com.nimbusds.jwt.JWTClaimsSet
import java.util.Date

/**
 * Data model for token claims
 */
data class TokenClaims(
        val uuid: String = "default",
        val auth_time: Long = 100,
        val issued: Date = Date(),
        val expire: Date = Date(),
        val name: String = "Test User",
        val cognitoUserName: String = "Test User",
        val email: String = "biztoi@biztoi.com"
) {
        constructor(details: JWTClaimsSet) : this(
            uuid = details.getStringClaim("sub"),
            auth_time = details.getClaim("auth_time") as Long,
            issued = details.getClaim("iat") as Date,
            expire = details.getClaim("exp") as Date,
            name = details.getClaim("email") as String,
            cognitoUserName = details.getClaim("cognito:username") as String,
            email = details.getStringClaim("email")
    )
}