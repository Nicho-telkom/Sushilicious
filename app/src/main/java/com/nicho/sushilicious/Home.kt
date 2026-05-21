package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

            startActivity(Intent(this, Home::class.java))

            finish()

        }

        // ================= NAV ORDER =================

        val navOrder = findViewById<ImageView>(R.id.navOrder)

        navOrder.setOnClickListener {

            startActivity(Intent(this, OrderHistory::class.java))

        }

        // ================= NAV CHAT =================

        val navChat = findViewById<ImageView>(R.id.navChat)

        navChat.setOnClickListener {

            startActivity(Intent(this, Home::class.java))

        }

        // ================= NAV NOTIF =================

        val navNotif = findViewById<ImageView>(R.id.navNotif)

        navNotif.setOnClickListener {

            startActivity(Intent(this, Home::class.java))

        }

        // ================= NAV PROFILE =================

        val navProfile = findViewById<ImageView>(R.id.navProfileBottom)

        navProfile.setOnClickListener {

            startActivity(Intent(this, profil::class.java))

        }
    }
}