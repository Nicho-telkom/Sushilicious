package com.nicho.sushilicious.model

data class SearchResponse(
    val status: Boolean,
    val message: String,
    val data: List<SushiResponse>
)