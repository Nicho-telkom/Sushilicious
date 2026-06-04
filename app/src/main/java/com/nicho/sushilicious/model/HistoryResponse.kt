package com.nicho.sushilicious.model

data class HistoryResponse(
    val status: Boolean,
    val message: String,
    val data: List<OrderData>
)