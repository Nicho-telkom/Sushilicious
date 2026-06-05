package com.nicho.sushilicious

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicho.sushilicious.model.NotificationItem

class NotificationAdapter(
    private val items: List<NotificationItem>
) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle  : TextView = view.findViewById(R.id.tvNotifTitle)
        val tvMessage: TextView = view.findViewById(R.id.tvNotifMessage)
        val tvDate   : TextView = view.findViewById(R.id.tvNotifDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvTitle.text   = item.title
        holder.tvMessage.text = item.message
        holder.tvDate.text    = item.created_at.take(16).replace("T", " • ")
        holder.itemView.alpha = if (item.is_read == 1) 0.6f else 1.0f
    }

    override fun getItemCount() = items.size
}