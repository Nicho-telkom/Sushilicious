package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView // Pastikan TextView ini ke-import otomatis
import androidx.appcompat.app.AppCompatActivity

class Cart : AppCompatActivity() {

    // 1. Buat variabel untuk menampung angka jumlah sushi (mulai dari 1)
    private var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // ================= BACK BUTTON =================
        val btnBack = findViewById<ImageButton>(R.id.imageButton)
        btnBack.setOnClickListener {
            finish()
        }

        // ================= PAYMENT BUTTON =================
        val btnPayment = findViewById<Button>(R.id.button2)
        btnPayment.setOnClickListener {
            val intent = Intent(this, PembayaranBerhasil::class.java)
            startActivity(intent)
            // finish()
        }

        // ================= LOGIKA TOMBOL QUANTITY =================
        // 2. Hubungkan ID TextView dari XML ke file Kotlin ini
        val btnMinus = findViewById<TextView>(R.id.btnMinus)
        val btnPlus = findViewById<TextView>(R.id.btnMinus2) // ID tombol plus sesuai XML kamu
        val textQuantity = findViewById<TextView>(R.id.textQuantity)

        // 3. Logika ketika Tombol Plus (+) diklik
        btnPlus.setOnClickListener {
            quantity++ // Menambah angka +1
            textQuantity.text = quantity.toString() // Tampilkan angka baru ke layar
        }

        // 4. Logika ketika Tombol Minus (-) diklik
        btnMinus.setOnClickListener {
            if (quantity > 1) { // Memastikan angka tidak bisa turun di bawah 1
                quantity-- // Mengurangi angka -1
                textQuantity.text = quantity.toString() // Tampilkan angka baru ke layar
            }
        }
    }
}