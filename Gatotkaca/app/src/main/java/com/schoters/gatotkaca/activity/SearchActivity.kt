package com.schoters.gatotkaca.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.schoters.gatotkaca.R

class SearchActivity : AppCompatActivity() {
    private lateinit var btnSearch : Button
    private lateinit var etSearch : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btnSearch = findViewById(R.id.btn_newsSearch)
        etSearch = findViewById(R.id.et_newsSearch)
        btnSearch.setOnClickListener{
            val searchString = etSearch.text.toString()
            if(searchString.trim().isEmpty()) {
                etSearch.error = "Belum diisi"
                return@setOnClickListener
            }
            val intentSearch = Intent(this@SearchActivity, MainActivity::class.java)
            intentSearch.putExtra(MainActivity.EXTRA_SEARCH, searchString)
            startActivity(intentSearch)
        }
    }
}