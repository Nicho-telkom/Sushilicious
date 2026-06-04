package com.nicho.sushilicious

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicho.sushilicious.model.SushiResponse

class SearchResultAdapter(
    private val items: List<SushiResponse>,
    private val onClick: (SushiResponse) -> Unit
) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvMenuName)
        val tvPrice: TextView = view.findViewById(R.id.tvMenuPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvName.text = item.name
        holder.tvPrice.text = "Rp ${String.format("%,.0f", item.price).replace(",", ".")}"
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = items.size
}