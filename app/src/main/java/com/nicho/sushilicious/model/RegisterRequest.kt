package com.nicho.sushilicious.model

data class RegisterRequest(
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val role: String,
    val phone: String,
    val address: String
)
