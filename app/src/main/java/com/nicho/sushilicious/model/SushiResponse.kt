package com.nicho.sushilicious.model

data class SushiResponse(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val description: String
)