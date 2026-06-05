package com.nicho.sushilicious

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nicho.sushilicious.model.SearchResponse
import com.nicho.sushilicious.model.SushiResponse
import com.nicho.sushilicious.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        // ================= API CALL =================
        getMenuFromServer()

        // ================= SEARCH =================
        val searchView = findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    searchMenu(query.trim())
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank() && newText.length >= 2) {
                    searchMenu(newText.trim())
                }
                return true
            }
        })

        // ================= CLICK UI =================
        findViewById<ImageView>(R.id.imageView6).setOnClickListener {
            startActivity(Intent(this, food::class.java))
        }

        findViewById<Button>(R.id.btnBuySalmon).setOnClickListener {
            startActivity(Intent(this, food::class.java))
        }

        findViewById<Button>(R.id.btnOrderNow).setOnClickListener {
            startActivity(Intent(this, food::class.java))
        }

        findViewById<ImageView>(R.id.navHome).setOnClickListener {
            Toast.makeText(this, "Kamu sudah di Home", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.navOrder).setOnClickListener {
            startActivity(Intent(this, OrderHistory::class.java))
        }

        findViewById<ImageView>(R.id.navChat).setOnClickListener {
            Toast.makeText(this, "Menu Chat belum tersedia", Toast.LENGTH_SHORT).show()
        }

        // ================= NOTIFIKASI =================
        findViewById<ImageView>(R.id.navNotif).setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }

        findViewById<ImageView>(R.id.navProfileBottom).setOnClickListener {
            startActivity(Intent(this, profil::class.java))
        }
    }

    // ================= SEARCH FUNCTION =================
    private fun searchMenu(keyword: String) {

        RetrofitClient.instance.searchMenus(keyword)
            .enqueue(object : Callback<SearchResponse> {

                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {

                        val results = response.body()?.data

                        if (results.isNullOrEmpty()) {
                            Toast.makeText(
                                this@Home,
                                "Menu '$keyword' tidak ditemukan",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val intent = Intent(this@Home, SearchResultActivity::class.java)
                            intent.putExtra("keyword", keyword)
                            intent.putParcelableArrayListExtra(
                                "results",
                                ArrayList(results)
                            )
                            startActivity(intent)
                        }

                    } else {
                        Toast.makeText(
                            this@Home,
                            "Menu tidak ditemukan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.e("SearchError", t.message ?: "Unknown error")
                    Toast.makeText(
                        this@Home,
                        "Gagal terhubung ke server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    // ================= POPULAR MENU FUNCTION =================
    private fun getMenuFromServer() {

        RetrofitClient.instance.getPopularSushi()
            .enqueue(object : Callback<List<SushiResponse>> {

                override fun onResponse(
                    call: Call<List<SushiResponse>>,
                    response: Response<List<SushiResponse>>
                ) {
                    if (response.isSuccessful) {
                        val sushiList = response.body()
                        if (!sushiList.isNullOrEmpty()) {
                            Log.d("RetrofitSuccess", "Data berhasil: ${sushiList[0].name}")
                        } else {
                            Log.d("RetrofitSuccess", "Data kosong dari server")
                        }
                    } else {
                        Log.e("RetrofitError", "Response gagal: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<SushiResponse>>, t: Throwable) {
                    Log.e("RetrofitError", "Gagal konek: ${t.message}")
                    Toast.makeText(
                        this@Home,
                        "Gagal terhubung ke server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}