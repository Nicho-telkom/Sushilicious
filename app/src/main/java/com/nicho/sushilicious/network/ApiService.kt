package com.nicho.sushilicious.network

// PENTING: Import ini agar ApiService mengenali SushiResponse kamu
import com.nicho.sushilicious.model.ApiResponse
import com.nicho.sushilicious.model.RegisterRequest
import com.nicho.sushilicious.model.LoginRequest
import com.nicho.sushilicious.model.SushiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("api/register")
    fun register(
        @Body request: RegisterRequest
    ): Call<ApiResponse>

    @POST("api/login")
    fun login(
        @Body request: LoginRequest
    ): Call<ApiResponse>

    @GET("api/sushi/popular")
    suspend fun getPopularSushi(): List<SushiResponse>

}