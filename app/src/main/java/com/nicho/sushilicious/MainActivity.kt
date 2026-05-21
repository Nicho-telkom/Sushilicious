package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // ================= COMPONENT =================

        val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)

        val btnGoogle = findViewById<ImageButton>(R.id.btnGoogle)

        val btnFacebook = findViewById<ImageButton>(R.id.btnFacebook)

        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)

        // ================= GET STARTED =================

        btnGetStarted.setOnClickListener {

            startActivity(Intent(this, SignUpActivity::class.java))

        }

        // ================= GOOGLE =================

        btnGoogle.setOnClickListener {

            Toast.makeText(
                this,
                "Google Login Clicked",
                Toast.LENGTH_SHORT
            ).show()

        }

        // ================= FACEBOOK =================

        btnFacebook.setOnClickListener {

            Toast.makeText(
                this,
                "Facebook Login Clicked",
                Toast.LENGTH_SHORT
            ).show()

        }

        // ================= SIGN UP =================

        tvSignUp.setOnClickListener {

            startActivity(Intent(this, SignUpActivity::class.java))

        }
    }
}