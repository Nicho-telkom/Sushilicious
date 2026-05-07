package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class profil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)


        findViewById<ImageView>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            finish()
        }
        findViewById<ImageView>(R.id.navOrder).setOnClickListener {
            startActivity(Intent(this, Cart::class.java))
        }
        findViewById<ImageView>(R.id.navChat).setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }
        findViewById<ImageView>(R.id.navNotif).setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }
        findViewById<ImageView>(R.id.navProfile).setOnClickListener {
            // tetap di profil
        }
    }
}
