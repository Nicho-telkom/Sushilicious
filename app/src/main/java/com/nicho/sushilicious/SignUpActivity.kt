package com.nicho.sushilicious

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)

        // ================= COMPONENT =================

        val etEmail = findViewById<EditText>(R.id.etEmail)

        val etPassword = findViewById<EditText>(R.id.etPassword)

        val etConfirmPassword =
            findViewById<EditText>(R.id.etConfirmPassword)

        val checkboxTerms =
            findViewById<CheckBox>(R.id.checkboxTerms)

        val btnSignUp =
            findViewById<Button>(R.id.btnSignUp)

        val tvLoginHere =
            findViewById<TextView>(R.id.tvLoginHere)

        // ================= SIGN UP =================

        btnSignUp.setOnClickListener {

            val email = etEmail.text.toString().trim()

            val password = etPassword.text.toString().trim()

            val confirmPassword =
                etConfirmPassword.text.toString().trim()

            // VALIDASI

            if (email.isEmpty() ||
                password.isEmpty() ||
                confirmPassword.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (password != confirmPassword) {

                Toast.makeText(
                    this,
                    "Password does not match",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (!checkboxTerms.isChecked) {

                Toast.makeText(
                    this,
                    "Please agree to terms",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // SAVE DATA

            val prefs =
                getSharedPreferences(
                    "USER_DATA",
                    Context.MODE_PRIVATE
                )

            val editor = prefs.edit()

            editor.putString("EMAIL", email)

            editor.putString("PASSWORD", password)

            editor.apply()

            Toast.makeText(
                this,
                "Sign Up Success",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()
        }

        // ================= LOGIN HERE =================

        tvLoginHere.setOnClickListener {

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

        }
    }
}