package com.yoshikiohashi.biztoi.configuration

import reactor.core.publisher.Mono
import org.springframework.security.core.Authentication
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.stereotype.Component


@Component
class AuthenticationManager : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        // JwtAuthenticationToken is my custom token.
        authentication.isAuthenticated = true
//        if (authentication is JwtAuthenticationToken) {
//        }
        return Mono.just(authentication)
    }
}
