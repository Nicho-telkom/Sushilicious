package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nicho.sushilicious.model.SushiResponse
import com.nicho.sushilicious.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ================= API CALL =================
        getMenuFromServer()

        // ================= CLICK UI =================
        findViewById<ImageView>(R.id.imageView6).setOnClickListener {
            startActivity(Intent(this, food::class.java))
        }

        findViewById<Button>(R.id.btnBuySalmon).setOnClickListener {
            startActivity(Intent(this, food::class.java))
        }

        findViewById<Button>(R.id.btnOrderNow).setOnClickListener {
            startActivity(Intent(this, food::class.java))
        }

        findViewById<ImageView>(R.id.navHome).setOnClickListener {
            Toast.makeText(this, "Kamu sudah di Home", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.navOrder).setOnClickListener {
            startActivity(Intent(this, OrderHistory::class.java))
        }

        findViewById<ImageView>(R.id.navChat).setOnClickListener {
            Toast.makeText(this, "Menu Chat belum tersedia", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.navNotif).setOnClickListener {
            Toast.makeText(this, "Menu Notifikasi belum tersedia", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.navProfileBottom).setOnClickListener {
            startActivity(Intent(this, profil::class.java))
        }
    }

    // ================= API FUNCTION =================
    private fun getMenuFromServer() {

        RetrofitClient.instance.getPopularSushi()
            .enqueue(object : Callback<List<SushiResponse>> {

                override fun onResponse(
                    call: Call<List<SushiResponse>>,
                    response: Response<List<SushiResponse>>
                ) {

                    if (response.isSuccessful) {

                        val sushiList = response.body()

                        // ✔️ FIX ERROR isNotEmpty
                        if (!sushiList.isNullOrEmpty()) {

                            val topSushi = sushiList[0]

                            Log.d(
                                "RetrofitSuccess",
                                "Data berhasil: ${topSushi.name}"
                            )

                        } else {
                            Log.d("RetrofitSuccess", "Data kosong dari server")
                        }

                    } else {
                        Log.e("RetrofitError", "Response gagal: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<SushiResponse>>, t: Throwable) {
                    Log.e("RetrofitError", "Gagal konek: ${t.message}")

                    Toast.makeText(
                        this@Home,
                        "Gagal terhubung ke server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}