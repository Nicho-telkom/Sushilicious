package com.nicho.sushilicious.network

import com.nicho.sushilicious.model.AddCartRequest
import com.nicho.sushilicious.model.ApiResponse
import com.nicho.sushilicious.model.CheckoutRequest
import com.nicho.sushilicious.model.CouponResponse
import com.nicho.sushilicious.model.HistoryResponse
import com.nicho.sushilicious.model.LoginRequest
import com.nicho.sushilicious.model.LoginResponse
import com.nicho.sushilicious.model.OrderResponse
import com.nicho.sushilicious.model.RegisterRequest
import com.nicho.sushilicious.model.SearchResponse
import com.nicho.sushilicious.model.SushiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Call<ApiResponse>

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("sushi/popular")
    fun getPopularSushi(): Call<List<SushiResponse>>

    @GET("menus/search")
    fun searchMenus(@Query("keyword") keyword: String): Call<SearchResponse>

    @POST("cart/items")
    fun addToCart(
        @Header("Authorization") token: String,
        @Body request: AddCartRequest
    ): Call<ApiResponse>

    @POST("coupon/redeem")
    fun redeemCoupon(
        @Header("Authorization") token: String,
        @Body request: Map<String, String>
    ): Call<CouponResponse>

    @POST("cart/checkout")
    fun checkout(
        @Header("Authorization") token: String,
        @Body request: CheckoutRequest
    ): Call<OrderResponse>

    @GET("orders")
    fun getOrderHistory(
        @Header("Authorization") token: String
    ): Call<HistoryResponse>
}