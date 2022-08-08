package com.schoters.gatotkaca.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.schoters.gatotkaca.R
import com.schoters.gatotkaca.adapter.EverythingAdapter
import com.schoters.gatotkaca.api.RetrofitClient
import com.schoters.gatotkaca.data.Everything
import com.schoters.gatotkaca.data.EverythingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rvList: RecyclerView
    private lateinit var tvError: TextView
    private var list: ArrayList<Everything> = arrayListOf()

    companion object {
        const val EXTRA_SEARCH = "extra_search"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showNewsEverything()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun showNewsEverything() {
        tvError = findViewById(R.id.tv_newsError)

        rvList = findViewById(R.id.rv_newsList)
        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(this)

        val parameterq = intent.getStringExtra(EXTRA_SEARCH)
        val parameters = HashMap<String, String>()
        parameters["apiKey"] = "137f16d98f8445aeac86188caf4e42f6"
        parameters["q"] = "${parameterq}"

        if(parameterq != "") {
            RetrofitClient.instance.getEverything(parameters)
                .enqueue(object : Callback<EverythingResponse> {
                    override fun onResponse(
                        call: Call<EverythingResponse>,
                        response: Response<EverythingResponse>
                    ) {
                        val listResponse = response.body()?.articles
                        listResponse?.let { list.addAll(it) }
                        val adapter = EverythingAdapter(list)
                        rvList.adapter = adapter
                    }

                    override fun onFailure(call: Call<EverythingResponse>, t: Throwable) {
                        tvError.visibility = View.VISIBLE
                        tvError.text = "Ups! Sepertinya ada masalah. Mohon kembali lagi nanti."
                    }
                })
        } else {
            tvError.visibility = View.VISIBLE
            tvError.text = "Ups! Anda lupa mengisi berita yang ingin dicari. Mohon kembali lagi."
        }
    }
}