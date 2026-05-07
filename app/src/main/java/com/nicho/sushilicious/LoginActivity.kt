package com.nicho.sushilicious

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val enteredEmail = etEmail.text.toString().trim()
            val enteredPassword = etPassword.text.toString().trim()

            val prefs = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
            val savedEmail = prefs.getString("EMAIL", null)
            val savedPassword = prefs.getString("PASSWORD", null)

            if (savedEmail == null || savedPassword == null) {
                Toast.makeText(this, "You need to Sign Up first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (enteredEmail == savedEmail && enteredPassword == savedPassword) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Home::class.java))
                finish()
            } else {
                Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
