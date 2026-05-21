package com.nicho.sushilicious

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class OrderHistory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_history)

        // ================= BACK BUTTON =================

        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }
    }
}