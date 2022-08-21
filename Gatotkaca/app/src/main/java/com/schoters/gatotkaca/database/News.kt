package com.schoters.gatotkaca.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "news_data")
data class News(
    @PrimaryKey
    @NonNull
    val title: String,
    val source: String,
    val author: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String
): Serializable