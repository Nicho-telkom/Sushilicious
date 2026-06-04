package com.nicho.sushilicious.model

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val role: String,
    val phone: String?,
    val address: String?
)
