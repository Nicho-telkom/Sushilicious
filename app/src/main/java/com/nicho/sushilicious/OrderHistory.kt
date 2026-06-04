package com.nicho.sushilicious

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicho.sushilicious.model.HistoryResponse
import com.nicho.sushilicious.model.OrderData
import com.nicho.sushilicious.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderHistory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_history)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        loadHistory()
    }

    private fun loadHistory() {
        val token = getSharedPreferences("SUSHI_APP", MODE_PRIVATE)
            .getString("TOKEN", null)

        if (token == null) {
            Toast.makeText(this, "Silakan login dulu", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        RetrofitClient.instance.getOrderHistory("Bearer $token")
            .enqueue(object : Callback<HistoryResponse> {

                override fun onResponse(
                    call: Call<HistoryResponse>,
                    response: Response<HistoryResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        val orders = response.body()?.data ?: emptyList()

                        val tvEmpty = findViewById<TextView>(R.id.tvEmpty)
                        val rvHistory = findViewById<RecyclerView>(R.id.rvOrderHistory)

                        if (orders.isEmpty()) {
                            tvEmpty.visibility   = View.VISIBLE
                            rvHistory.visibility = View.GONE
                        } else {
                            tvEmpty.visibility   = View.GONE
                            rvHistory.visibility = View.VISIBLE
                            rvHistory.layoutManager = LinearLayoutManager(this@OrderHistory)
                            rvHistory.adapter = OrderHistoryAdapter(orders)
                        }
                    } else {
                        Toast.makeText(
                            this@OrderHistory,
                            "Gagal memuat riwayat",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                    Toast.makeText(
                        this@OrderHistory,
                        "Error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}