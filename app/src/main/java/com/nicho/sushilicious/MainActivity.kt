package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)
        val btnGoogle = findViewById<ImageButton>(R.id.btnGoogle)
        val btnFacebook = findViewById<ImageButton>(R.id.btnFacebook)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)

        btnGetStarted.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnGoogle.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnFacebook.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        tvSignUp.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
