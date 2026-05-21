package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class food : AppCompatActivity() {

    private var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        // ================= QUANTITY =================

        val btnMinus = findViewById<TextView>(R.id.btnMinus)
        val btnPlus = findViewById<TextView>(R.id.btnMinus2)
        val textQuantity = findViewById<TextView>(R.id.textQuantity)

        btnMinus.setOnClickListener {

            if (quantity > 1) {
                quantity--
                textQuantity.text = quantity.toString()
            }

        }

        btnPlus.setOnClickListener {

            quantity++
            textQuantity.text = quantity.toString()

        }

        // ================= ADD TO CART =================

        val btnAddCart = findViewById<Button>(R.id.button3)

        btnAddCart.setOnClickListener {

            Toast.makeText(
                this,
                "Added $quantity Salmon Sushi to Cart",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(Intent(this, Cart::class.java))

        }
    }
}