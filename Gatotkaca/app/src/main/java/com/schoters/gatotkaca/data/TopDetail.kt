package com.schoters.gatotkaca.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopDetail(
    val source: String,
    val title: String,
    val author: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String
): Parcelable
