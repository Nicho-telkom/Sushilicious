package com.nicho.sushilicious.model

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: User?
)
