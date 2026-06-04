package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nicho.sushilicious.model.AddCartRequest
import com.nicho.sushilicious.model.ApiResponse
import com.nicho.sushilicious.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class food : AppCompatActivity() {

    private var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        // ================= QUANTITY =================

        val btnMinus = findViewById<TextView>(R.id.btnMinus)
        val btnPlus = findViewById<TextView>(R.id.btnMinus2)
        val textQuantity = findViewById<TextView>(R.id.textQuantity)

        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                textQuantity.text = quantity.toString()
            }
        }

        btnPlus.setOnClickListener {
            quantity++
            textQuantity.text = quantity.toString()
        }

        // ================= ADD TO CART =================

        val btnAddCart = findViewById<Button>(R.id.button3)

        btnAddCart.setOnClickListener {

            val prefs = getSharedPreferences(
                "SUSHI_APP",
                MODE_PRIVATE
            )

            val token = prefs.getString("TOKEN", null)

            if (token == null) {
                Toast.makeText(
                    this,
                    "Silakan login dulu",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            Log.d("TOKEN_DEBUG", "Bearer $token")

            val request = AddCartRequest(
                menu_id = 1,
                quantity = quantity,
                notes = null
            )

            RetrofitClient.instance.addToCart(
                "Bearer $token",
                request
            ).enqueue(object : Callback<ApiResponse> {

                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {

                    if (response.isSuccessful) {

                        Toast.makeText(
                            this@food,
                            "Berhasil ditambahkan ke cart",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(
                            Intent(
                                this@food,
                                Cart::class.java
                            )
                        )

                    } else {

                        val errorBody =
                            response.errorBody()?.string()

                        Log.e(
                            "API_ERROR",
                            "Code: ${response.code()} | Body: $errorBody"
                        )

                        Toast.makeText(
                            this@food,
                            "Error ${response.code()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(
                    call: Call<ApiResponse>,
                    t: Throwable
                ) {

                    Log.e(
                        "API_FAILURE",
                        t.message ?: "Unknown Error"
                    )

                    Toast.makeText(
                        this@food,
                        "Error: ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
}