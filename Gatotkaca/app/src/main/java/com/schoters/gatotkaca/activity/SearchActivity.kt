package com.schoters.gatotkaca.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.schoters.gatotkaca.R
import com.schoters.gatotkaca.data.Everything
import com.schoters.gatotkaca.data.EverythingSource
import com.schoters.gatotkaca.database.DatabaseViewModel
import com.schoters.gatotkaca.database.News

class SearchActivity : AppCompatActivity() {
    private lateinit var mbFavorite: MaterialButton
    private lateinit var btnSearch: Button
    private lateinit var etSearch: EditText
    private lateinit var viewModel: DatabaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mbFavorite = findViewById(R.id.mb_favorite)
        btnSearch = findViewById(R.id.btn_newsSearch)
        etSearch = findViewById(R.id.et_newsSearch)
        viewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)

        viewModel.getNews().observe(this, {
            if(it != null) {
                val list = mapList(it)
                val size = list.size.toString()
                mbFavorite.text = size
            }
        })

        btnSearch.setOnClickListener {
            val searchString = etSearch.text.toString()
            if(searchString.trim().isEmpty()) {
                etSearch.error = "Belum diisi"
                return@setOnClickListener
            }
            val intentSearch = Intent(this@SearchActivity, MainActivity::class.java)
            intentSearch.putExtra(MainActivity.EXTRA_SEARCH, searchString)
            startActivity(intentSearch)
        }

        mbFavorite.setOnClickListener {
            val intentFavorite = Intent(this@SearchActivity, FavoriteActivity::class.java)
            startActivity(intentFavorite)
        }
    }

    private fun mapList(news: List<News>): ArrayList<Everything> {
        val list = ArrayList<Everything>()
        for(favorite in news) {
            val favoriteSource = EverythingSource(favorite.source)
            val listMap = Everything(
                favoriteSource,
                favorite.title,
                favorite.author,
                favorite.description,
                favorite.urlToImage,
                favorite.publishedAt
            )
            list.add(listMap)
        }
        return list
    }
}