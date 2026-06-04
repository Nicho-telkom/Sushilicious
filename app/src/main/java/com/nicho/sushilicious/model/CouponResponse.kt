package com.nicho.sushilicious.model

data class CouponResponse(
    val status: Boolean,
    val message: String,
    val discount: Int?
)