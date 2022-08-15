package com.schoters.gatotkaca.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.schoters.gatotkaca.R
import com.schoters.gatotkaca.adapter.TopAdapter
import com.schoters.gatotkaca.api.RetrofitClient
import com.schoters.gatotkaca.data.Top
import com.schoters.gatotkaca.data.TopResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopFragment : Fragment() {
    private lateinit var rvList: RecyclerView
    private var list: ArrayList<Top> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showNewsTop()
    }

    private fun showNewsTop() {
        rvList = requireView().findViewById(R.id.rv_newsTopList)
        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(activity)

        val parameterq = "us"
        val parameters = HashMap<String, String>()
        parameters["apiKey"] = "137f16d98f8445aeac86188caf4e42f6"
        parameters["q"] = "${parameterq}"

        RetrofitClient.instance.getTop(parameters).enqueue(object :
            Callback<TopResponse> {
            override fun onResponse(call: Call<TopResponse>, response: Response<TopResponse>) {
                val listResponse = response.body()?.articles
                listResponse?.let { list.addAll(it) }
                val adapter = TopAdapter(list)
                rvList.adapter = adapter
            }

            override fun onFailure(call: Call<TopResponse>, t: Throwable) {
            }
        })
    }
}