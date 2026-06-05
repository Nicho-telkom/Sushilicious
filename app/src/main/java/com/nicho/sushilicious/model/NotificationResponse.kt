package com.nicho.sushilicious.model

data class NotificationResponse(
    val status: Boolean,
    val message: String,
    val data: List<NotificationItem>
)

data class NotificationItem(
    val id: Int,
    val user_id: Int,
    val title: String,
    val message: String,
    val is_read: Int,  // ganti Boolean jadi Int
    val created_at: String
)