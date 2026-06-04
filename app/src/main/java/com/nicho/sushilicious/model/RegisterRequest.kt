package com.nicho.sushilicious.model

data class RegisterRequest(
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val password_confirmation: String, // TAMBAHKAN BARIS INI
    val role: String,
    val phone: String,
    val address: String
)