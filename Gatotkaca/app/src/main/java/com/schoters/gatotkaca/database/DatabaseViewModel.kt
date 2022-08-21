package com.schoters.gatotkaca.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {
    private var newsDao: NewsDao?
    private var newsDb: NewsDatabase? = NewsDatabase.getDatabase(application)
    private var list: LiveData<List<News>>

    init {
        newsDao = newsDb?.newsDao()
        list = newsDao?.getNews()!!
    }

    fun insertNews(
        title: String,
        source: String,
        author: String,
        description: String,
        urlToImage: String,
        publishedAt: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val news = News(
                title,
                source,
                author,
                description,
                urlToImage,
                publishedAt
            )
            newsDao?.insertNews(news)
        }
    }

    fun getNews(): LiveData<List<News>> = list

    fun checkNews(title: String) = newsDao?.checkNews(title)

    fun removeNews(title: String) {
        CoroutineScope(Dispatchers.IO).launch {
            newsDao?.removeNews(title)
        }
    }
}