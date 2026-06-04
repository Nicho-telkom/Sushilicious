package com.nicho.sushilicious

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicho.sushilicious.model.OrderData

class OrderHistoryAdapter(
    private val orders: List<OrderData>
) : RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvOrderCode  : TextView = view.findViewById(R.id.tvOrderCode)
        val tvOrderName  : TextView = view.findViewById(R.id.tvOrderName)
        val tvOrderTotal : TextView = view.findViewById(R.id.tvOrderTotal)
        val tvOrderStatus: TextView = view.findViewById(R.id.tvOrderStatus)
        val tvOrderDate  : TextView = view.findViewById(R.id.tvOrderDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]

        holder.tvOrderCode.text   = order.order_code
        holder.tvOrderName.text   = order.customer_name
        holder.tvOrderTotal.text  = "Rp ${String.format("%,.0f", order.total_price).replace(",", ".")}"
        holder.tvOrderDate.text   = order.created_at.take(16).replace("T", " • ")

        // Warna status
        when (order.status) {
            "completed" -> {
                holder.tvOrderStatus.text = "Completed"
                holder.tvOrderStatus.setBackgroundColor(0xFFD9F8E5.toInt())
                holder.tvOrderStatus.setTextColor(0xFF1B8E3E.toInt())
            }
            "cancelled" -> {
                holder.tvOrderStatus.text = "Cancelled"
                holder.tvOrderStatus.setBackgroundColor(0xFFFFE0E0.toInt())
                holder.tvOrderStatus.setTextColor(0xFFCC0000.toInt())
            }
            else -> {
                holder.tvOrderStatus.text = order.status
                holder.tvOrderStatus.setBackgroundColor(0xFFFFF0D9.toInt())
                holder.tvOrderStatus.setTextColor(0xFFCC5F00.toInt())
            }
        }
    }

    override fun getItemCount() = orders.size
}