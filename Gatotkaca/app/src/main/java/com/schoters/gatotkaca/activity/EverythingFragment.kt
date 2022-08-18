package com.schoters.gatotkaca.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.schoters.gatotkaca.R
import com.schoters.gatotkaca.adapter.EverythingAdapter
import com.schoters.gatotkaca.data.Everything
import com.schoters.gatotkaca.data.SharedViewModel

class EverythingFragment : Fragment() {
    private lateinit var tvError: TextView
    private lateinit var rvList: RecyclerView
    private lateinit var adapter: EverythingAdapter
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_everything, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvError = view.findViewById(R.id.tv_newsError)
        rvList = view.findViewById(R.id.rv_newsEverythingList)
        adapter = EverythingAdapter()
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(SharedViewModel::class.java)

        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(activity)
        rvList.adapter = adapter

        val parameterq = requireActivity().intent.getStringExtra("extra_search").toString()

        viewModel.setEverything(parameterq)
        viewModel.getEverything().observe(viewLifecycleOwner, {
            if(it.size == 0) {
                tvError.visibility = View.VISIBLE
                tvError.text = "Ups! Tidak ditemukan berita.\nMohon kembali lagi."
            }
            adapter.setList(it)
        })

        viewModel.getErrorEverything().observe(viewLifecycleOwner, {
            if(it != "") {
                tvError.visibility = View.VISIBLE
                tvError.text = it
            }
        })

        adapter.setOnItemClickCallback(object : EverythingAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Everything) {
                showDetailNews(data)
            }
        })
    }

    private fun showDetailNews(everything: Everything) {
        Toast.makeText(activity, "Kamu memilih" + everything.source.name, Toast.LENGTH_SHORT).show()
    }
}