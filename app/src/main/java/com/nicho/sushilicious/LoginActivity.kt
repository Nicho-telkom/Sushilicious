package com.nicho.sushilicious

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nicho.sushilicious.model.ApiResponse
import com.nicho.sushilicious.model.LoginRequest
import com.nicho.sushilicious.network.RetrofitClient

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

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            val request = LoginRequest(
                email = email,
                password = password
            )

            RetrofitClient.instance.login(request)
                .enqueue(object : retrofit2.Callback<ApiResponse> {

                    override fun onResponse(
                        call: retrofit2.Call<ApiResponse>,
                        response: retrofit2.Response<ApiResponse>
                    ) {

                        if (response.isSuccessful &&
                            response.body()?.success == true
                        ) {

                            Toast.makeText(
                                this@LoginActivity,
                                "Login Success",
                                Toast.LENGTH_SHORT
                            ).show()

                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    Home::class.java
                                )
                            )

                            finish()

                        } else {

                            Toast.makeText(
                                this@LoginActivity,
                                "Email atau password salah",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }

                    override fun onFailure(
                        call: retrofit2.Call<ApiResponse>,
                        t: Throwable
                    ) {

                        Toast.makeText(
                            this@LoginActivity,
                            t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }
}
