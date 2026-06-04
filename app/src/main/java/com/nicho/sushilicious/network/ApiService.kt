package com.nicho.sushilicious.network

import com.nicho.sushilicious.model.ApiResponse
import com.nicho.sushilicious.model.RegisterRequest
import com.nicho.sushilicious.model.LoginRequest
import com.nicho.sushilicious.model.SushiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // Hapus tulisan "api/" nya, sisakan "register" saja
    @POST("register")
    fun register(
        @Body request: RegisterRequest
    ): Call<ApiResponse>

    // Sisakan "login" saja
    @POST("login")
    fun login(
        @Body request: LoginRequest
    ): Call<ApiResponse>

    // Sisakan "sushi/popular" saja
    @GET("sushi/popular")
    suspend fun getPopularSushi(): List<SushiResponse>
}