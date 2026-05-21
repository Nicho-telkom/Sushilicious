package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class profil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        // ================= ORDER HISTORY =================

        val menuOrderHistory =
            findViewById<LinearLayout>(R.id.menuOrderHistory)

        menuOrderHistory.setOnClickListener {

            startActivity(
                Intent(this, OrderHistory::class.java)
            )

        }
    }
}