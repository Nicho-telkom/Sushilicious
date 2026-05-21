package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Cart : AppCompatActivity() {

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

            startActivity(Intent(this, PembayaranBerhasil::class.java))

        }
    }
}