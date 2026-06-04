package com.nicho.sushilicious.model

data class LoginResponse(
    val status: Boolean,
    val message: String,
    val token: String,
    val user: Any? = null
)
