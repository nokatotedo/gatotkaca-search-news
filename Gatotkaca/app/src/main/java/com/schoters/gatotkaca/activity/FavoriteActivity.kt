package com.schoters.gatotkaca.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.schoters.gatotkaca.R

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        setActionBar()
    }

    fun setActionBar() {
        val title = "Favorite News"
        supportActionBar!!.title = title
    }
}