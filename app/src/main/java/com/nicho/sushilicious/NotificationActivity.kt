package com.nicho.sushilicious

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicho.sushilicious.model.NotificationResponse
import com.nicho.sushilicious.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        loadNotifications()
    }

    private fun loadNotifications() {
        val token = getSharedPreferences("SUSHI_APP", MODE_PRIVATE)
            .getString("TOKEN", null)

        if (token == null) {
            Toast.makeText(this, "Silakan login dulu", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        RetrofitClient.instance.getNotifications("Bearer $token")
            .enqueue(object : Callback<NotificationResponse> {

                override fun onResponse(
                    call: Call<NotificationResponse>,
                    response: Response<NotificationResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        val notifs = response.body()?.data ?: emptyList()

                        val tvEmpty = findViewById<TextView>(R.id.tvEmpty)
                        val rvNotif = findViewById<RecyclerView>(R.id.rvNotifications)

                        if (notifs.isEmpty()) {
                            tvEmpty.visibility = View.VISIBLE
                            rvNotif.visibility = View.GONE
                        } else {
                            tvEmpty.visibility = View.GONE
                            rvNotif.visibility = View.VISIBLE
                            rvNotif.layoutManager = LinearLayoutManager(this@NotificationActivity)
                            rvNotif.adapter = NotificationAdapter(notifs)
                        }
                    } else {
                        Toast.makeText(
                            this@NotificationActivity,
                            "Gagal memuat notifikasi",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {
                    Toast.makeText(
                        this@NotificationActivity,
                        "Error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}