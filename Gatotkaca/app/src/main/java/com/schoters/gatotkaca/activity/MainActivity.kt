package com.schoters.gatotkaca.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.schoters.gatotkaca.R

class MainActivity : AppCompatActivity() {
    private lateinit var nvBottom: BottomNavigationView

    companion object {
        const val EXTRA_SEARCH = "extra_search"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nvBottom = findViewById(R.id.nv_newsBottom)
        val nvController = supportFragmentManager.findFragmentById(R.id.fv_news)?.findNavController()
        nvController?.let { nvBottom.setupWithNavController(it) }
    }
}