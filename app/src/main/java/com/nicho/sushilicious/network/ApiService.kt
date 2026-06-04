package com.nicho.sushilicious.network

import com.nicho.sushilicious.model.AddCartRequest
import com.nicho.sushilicious.model.ApiResponse
import com.nicho.sushilicious.model.LoginRequest
import com.nicho.sushilicious.model.LoginResponse
import com.nicho.sushilicious.model.RegisterRequest
import com.nicho.sushilicious.model.SushiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    // REGISTER
    @POST("auth/register")
    fun register(
        @Body request: RegisterRequest
    ): Call<ApiResponse>

    // LOGIN
    @POST("auth/login")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    // MENU
    @GET("sushi/popular")
    fun getPopularSushi(): Call<List<SushiResponse>>

    // ADD TO CART
    @POST("cart/items")
    fun addToCart(
        @Header("Authorization") token: String,
        @Body request: AddCartRequest
    ): Call<ApiResponse>
}