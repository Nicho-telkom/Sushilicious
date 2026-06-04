package com.nicho.sushilicious.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SushiResponse(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String?,        // nullable
    val description: String?      // nullable juga antisipasi
) : Parcelable