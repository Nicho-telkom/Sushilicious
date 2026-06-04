package com.nicho.sushilicious.model

data class OrderResponse(
    val status: Boolean,
    val message: String,
    val order_code: String?,
    val total_price: Double?,
    val data: OrderData?
)

data class OrderData(
    val id: Int,
    val order_code: String,
    val customer_name: String,
    val address: String,
    val total_price: Double,
    val status: String,
    val created_at: String,
    val items: List<OrderItem>?
)

data class OrderItem(
    val id: Int,
    val menu_id: Int,
    val quantity: Int,
    val price: Double,
    val menu: MenuData?
)

data class MenuData(
    val id: Int,
    val name: String,
    val price: Double
)