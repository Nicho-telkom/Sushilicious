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
import com.nicho.sushilicious.model.ApiResponse
import com.nicho.sushilicious.model.RegisterRequest
import com.nicho.sushilicious.network.RetrofitClient

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // ================= COMPONENT =================
        val etName = findViewById<EditText>(R.id.etName)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val checkboxTerms = findViewById<CheckBox>(R.id.checkboxTerms)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val tvLoginHere = findViewById<TextView>(R.id.tvLoginHere)

        // ================= SIGN UP =================
        btnSignUp.setOnClickListener {
            val name = etName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            // VALIDASI INPUT
            if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!checkboxTerms.isChecked) {
                Toast.makeText(this, "Please agree to terms", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // SAVE DATA
            val request = RegisterRequest(
                name = name,
                username = username,
                email = email,
                password = password,
                password_confirmation = confirmPassword, // Disamakan dengan input confirmPassword user
                role = "user",
                phone = "08123456789",
                address = "Indonesia"
            )

            // Menjalankan request ke backend secara asynchronous
            RetrofitClient.instance.register(request)
                .enqueue(object : retrofit2.Callback<ApiResponse> {

                    override fun onResponse(
                        call: retrofit2.Call<ApiResponse>,
                        response: retrofit2.Response<ApiResponse>
                    ) {
                        if (response.isSuccessful && response.body()?.success == true) {
                            Toast.makeText(this@SignUpActivity, "Register Success", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                            finish()
                        } else {
                            // ================= UBAHAN DI SINI =================
                            // Membongkar pesan error validasi asli dari body server Laravel
                            val errorBody = response.errorBody()?.string()

                            Toast.makeText(
                                this@SignUpActivity,
                                "Ditolak Laravel: $errorBody",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<ApiResponse>, t: Throwable) {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Server Error: ${t.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }

        // ================= LOGIN HERE =================
        tvLoginHere.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}