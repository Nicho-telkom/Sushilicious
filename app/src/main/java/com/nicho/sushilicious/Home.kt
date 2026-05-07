package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val salmonImage = findViewById<ImageView>(R.id.imageView6)
        salmonImage.setOnClickListener {
            startActivity(Intent(this, food::class.java))
        }


        findViewById<ImageView>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            finish()
        }

        findViewById<ImageView>(R.id.navOrder).setOnClickListener {
            startActivity(Intent(this, Cart::class.java))
        }

        findViewById<ImageView>(R.id.navChat).setOnClickListener {
            startActivity(Intent(this, Home::class.java)) // nanti bisa diganti
        }

        findViewById<ImageView>(R.id.navNotif).setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }

        findViewById<ImageView>(R.id.navProfile).setOnClickListener {
            startActivity(Intent(this, profil::class.java))
        }
    }
}
