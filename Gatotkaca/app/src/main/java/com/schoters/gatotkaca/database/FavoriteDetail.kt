package com.schoters.gatotkaca.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteDetail(
    val source: String,
    val title: String,
    val author: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String
): Parcelable