package com.yoshikiohashi.biztoi.model

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
)