package com.yoshikiohashi.biztoi.controllers

import com.yoshikiohashi.biztoi.model.TokenClaims
import com.yoshikiohashi.biztoi.service.AuthService

import org.springframework.web.bind.annotation.RestController

/**
 * User endpoints, require authentication
 */
@RestController
//@RequestMapping("/user")
class UserController(val authService: AuthService) {
//    @GetMapping("/me")
    fun getCurrentUser(): TokenClaims {
        return authService.getClaims()
    }
}