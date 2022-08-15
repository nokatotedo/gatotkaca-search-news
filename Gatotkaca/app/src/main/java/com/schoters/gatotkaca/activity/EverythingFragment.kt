package com.schoters.gatotkaca.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class EverythingFragment : Fragment() {
    private lateinit var rvList: RecyclerView
    private var list: ArrayList<Everything> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_everything, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showNewsEverything()
    }

    private fun showNewsEverything() {
        rvList = requireView().findViewById(R.id.rv_newsEverythingList)
        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(activity)

        val parameterq = requireActivity().intent.getStringExtra("extra_search")
        val parameters = HashMap<String, String>()
        parameters["apiKey"] = "137f16d98f8445aeac86188caf4e42f6"
        parameters["q"] = "${parameterq}"

        RetrofitClient.instance.getEverything(parameters).enqueue(object :
            Callback<EverythingResponse> {
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
            }
        })
    }
}