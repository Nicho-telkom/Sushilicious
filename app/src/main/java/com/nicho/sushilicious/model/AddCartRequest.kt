package com.nicho.sushilicious.model

data class AddCartRequest(
    val menu_id: Int,
    val quantity: Int,
    val notes: String? = null
)