package com.nicho.sushilicious.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status")
    val success: Boolean, // Variabel Android kamu tetap pakai 'success' biar LoginActivity gak error
    val message: String,
    val data: Any? = null
)