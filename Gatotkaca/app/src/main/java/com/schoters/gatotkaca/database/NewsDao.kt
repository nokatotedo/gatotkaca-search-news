package com.schoters.gatotkaca.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert
    fun insertNews(news: News)

    @Query("SELECT * FROM news_data")
    fun getNews(): LiveData<List<News>>

    @Query("SELECT count(*) FROM news_data WHERE news_data.title = :title")
    fun checkNews(title: String): Int

    @Query("DELETE FROM news_data WHERE news_data.title = :title")
    fun removeNews(title: String): Int
}