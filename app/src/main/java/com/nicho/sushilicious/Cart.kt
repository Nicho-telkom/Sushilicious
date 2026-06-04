package com.nicho.sushilicious

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.nicho.sushilicious.model.CheckoutRequest
import com.nicho.sushilicious.model.CouponResponse
import com.nicho.sushilicious.model.OrderResponse
import com.nicho.sushilicious.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Cart : AppCompatActivity() {

    private var quantity = 1
    private val basePrice = 159000
    private var discountAmount = 0

    private lateinit var tvSubtotal: TextView
    private lateinit var tvDiskon: TextView
    private lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        tvSubtotal = findViewById(R.id.tvSubtotal)
        tvDiskon   = findViewById(R.id.tvDiskon)
        tvTotal    = findViewById(R.id.tvTotal)

        updatePriceSummary()

        // ================= BACK BUTTON =================
        findViewById<ImageButton>(R.id.imageButton).setOnClickListener {
            finish()
        }

        // ================= PAYMENT BUTTON =================
        findViewById<Button>(R.id.button2).setOnClickListener {
            showCheckoutDialog()
        }

        // ================= QUANTITY =================
        val textQuantity = findViewById<TextView>(R.id.textQuantity)

        findViewById<TextView>(R.id.btnMinus2).setOnClickListener {
            quantity++
            textQuantity.text = quantity.toString()
            updatePriceSummary()
        }

        findViewById<TextView>(R.id.btnMinus).setOnClickListener {
            if (quantity > 1) {
                quantity--
                textQuantity.text = quantity.toString()
                updatePriceSummary()
            }
        }

        // ================= COUPON =================
        val etCoupon = findViewById<EditText>(R.id.etCoupon)

        findViewById<Button>(R.id.button).setOnClickListener {
            val code = etCoupon.text.toString().trim()

            if (code.isEmpty()) {
                Toast.makeText(this, "Masukan kode kupon dulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val token = getSharedPreferences("SUSHI_APP", MODE_PRIVATE)
                .getString("TOKEN", null)

            if (token == null) {
                Toast.makeText(this, "Silakan login dulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            RetrofitClient.instance.redeemCoupon(
                "Bearer $token",
                mapOf("code" to code)
            ).enqueue(object : Callback<CouponResponse> {

                override fun onResponse(
                    call: Call<CouponResponse>,
                    response: Response<CouponResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        discountAmount = response.body()?.discount ?: 0
                        updatePriceSummary()
                        Toast.makeText(
                            this@Cart,
                            "Kupon berhasil! Diskon Rp ${formatRupiah(discountAmount)}",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(this@Cart, "Kupon tidak valid", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<CouponResponse>, t: Throwable) {
                    Toast.makeText(this@Cart, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    // ================= CHECKOUT DIALOG =================
    private fun showCheckoutDialog() {
        val token = getSharedPreferences("SUSHI_APP", MODE_PRIVATE)
            .getString("TOKEN", null)

        if (token == null) {
            Toast.makeText(this, "Silakan login dulu", Toast.LENGTH_SHORT).show()
            return
        }

        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_checkout, null)

        val etName    = dialogView.findViewById<EditText>(R.id.etCheckoutName)
        val etAddress = dialogView.findViewById<EditText>(R.id.etCheckoutAddress)
        val rgPayment = dialogView.findViewById<RadioGroup>(R.id.rgPaymentMethod)

        AlertDialog.Builder(this)
            .setTitle("Detail Pesanan")
            .setView(dialogView)
            .setPositiveButton("Pesan Sekarang") { _, _ ->

                val name    = etName.text.toString().trim()
                val address = etAddress.text.toString().trim()

                val paymentMethod = when (rgPayment.checkedRadioButtonId) {
                    R.id.rbCash     -> "cash"
                    R.id.rbTransfer -> "transfer"
                    R.id.rbQris     -> "qris"
                    else            -> ""
                }

                if (name.isEmpty() || address.isEmpty() || paymentMethod.isEmpty()) {
                    Toast.makeText(this, "Lengkapi semua data dulu", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                doCheckout(token, name, address, paymentMethod)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    // ================= HIT API CHECKOUT =================
    private fun doCheckout(token: String, name: String, address: String, paymentMethod: String) {

        val request = CheckoutRequest(
            customer_name  = name,
            address        = address,
            payment_method = paymentMethod
        )

        RetrofitClient.instance.checkout("Bearer $token", request)
            .enqueue(object : Callback<OrderResponse> {

                override fun onResponse(
                    call: Call<OrderResponse>,
                    response: Response<OrderResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        val orderCode = response.body()?.order_code ?: "-"
                        Toast.makeText(
                            this@Cart,
                            "Pesanan berhasil! Kode: $orderCode",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(this@Cart, PembayaranBerhasil::class.java))
                        finish()
                    } else {
                        val msg = response.errorBody()?.string() ?: "Gagal checkout"
                        Toast.makeText(this@Cart, msg, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                    Toast.makeText(this@Cart, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun updatePriceSummary() {
        val subtotal = basePrice * quantity
        val total    = maxOf(0, subtotal - discountAmount)

        tvSubtotal.text = "Rp ${formatRupiah(subtotal)}"
        tvDiskon.text   = "- Rp ${formatRupiah(discountAmount)}"
        tvTotal.text    = "Rp ${formatRupiah(total)}"
    }

    private fun formatRupiah(amount: Int): String {
        return String.format("%,d", amount).replace(",", ".")
    }
}