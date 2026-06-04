package com.nicho.sushilicious

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicho.sushilicious.model.SushiResponse

class SearchResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val keyword = intent.getStringExtra("keyword") ?: ""
        val results = intent.getParcelableArrayListExtra<SushiResponse>("results") ?: arrayListOf()

        findViewById<TextView>(R.id.tvSearchTitle).text = "Hasil: \"$keyword\""

        if (results.isEmpty()) {
            Toast.makeText(this, "Tidak ada hasil", Toast.LENGTH_SHORT).show()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rvSearchResults)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SearchResultAdapter(results) { selectedItem ->
            // Nanti bisa diarahkan ke detail menu / food activity
            Toast.makeText(this, "Kamu pilih: ${selectedItem.name}", Toast.LENGTH_SHORT).show()
        }
    }
}