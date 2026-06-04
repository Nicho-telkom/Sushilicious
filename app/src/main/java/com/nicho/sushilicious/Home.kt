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
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        // ================= WINDOW INSETS =================
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        // ================= RETROFIT CALL =================
        getMenuFromServer()

        // ================= SALMON IMAGE =================
        val salmonImage = findViewById<ImageView>(R.id.imageView6)
        salmonImage.setOnClickListener {
            startActivity(Intent(this, food::class.java))
        }

        // ================= BUY BUTTON =================
        val btnBuySalmon = findViewById<Button>(R.id.btnBuySalmon)
        btnBuySalmon.setOnClickListener {
            startActivity(Intent(this, food::class.java))
        }

        // ================= ORDER NOW BUTTON =================
        val btnOrderNow = findViewById<Button>(R.id.btnOrderNow)
        btnOrderNow.setOnClickListener {
            startActivity(Intent(this, food::class.java))
        }

        // ================= NAV HOME =================
        val navHome = findViewById<ImageView>(R.id.navHome)
        navHome.setOnClickListener {
            Toast.makeText(this, "Kamu sudah di Home", Toast.LENGTH_SHORT).show()
        }

        // ================= NAV ORDER =================
        val navOrder = findViewById<ImageView>(R.id.navOrder)
        navOrder.setOnClickListener {
            startActivity(Intent(this, OrderHistory::class.java))
        }

        // ================= NAV CHAT =================
        val navChat = findViewById<ImageView>(R.id.navChat)
        navChat.setOnClickListener {
            Toast.makeText(this, "Menu Chat belum tersedia", Toast.LENGTH_SHORT).show()
        }

        // ================= NAV NOTIF =================
        val navNotif = findViewById<ImageView>(R.id.navNotif)
        navNotif.setOnClickListener {
            Toast.makeText(this, "Menu Notifikasi belum tersedia", Toast.LENGTH_SHORT).show()
        }

        // ================= NAV PROFILE =================
        val navProfile = findViewById<ImageView>(R.id.navProfileBottom)
        navProfile.setOnClickListener {
            startActivity(Intent(this, profil::class.java))
        }
    }

    private fun getMenuFromServer() {
        lifecycleScope.launch {
            try {
                // 1. AMBIL DATA DARI SERVER
                val sushiList = com.nicho.sushilicious.network.RetrofitClient.instance.getPopularSushi()

                // 2. CEK APAKAH DATANYA ADA ATAU KOSONG
                if (sushiList.isNotEmpty()) {
                    val topSushi = sushiList[0]
                    Log.d("RetrofitSuccess", "Data berhasil dimuat: ${topSushi.name}")
                }

            } catch (e: Exception) {
                Log.e("RetrofitError", "Gagal mengambil data: ${e.message}")
                Toast.makeText(this@Home, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show()
            }
        }
    }
}