package com.nicho.sushilicious.model

data class CheckoutRequest(
    val customer_name: String,
    val address: String,
    val payment_method: String
)