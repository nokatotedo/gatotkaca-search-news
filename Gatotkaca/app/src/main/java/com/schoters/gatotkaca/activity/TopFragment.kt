package com.schoters.gatotkaca.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.schoters.gatotkaca.R
import com.schoters.gatotkaca.adapter.TopAdapter
import com.schoters.gatotkaca.data.SharedViewModel
import com.schoters.gatotkaca.data.Top

class TopFragment : Fragment() {
    private lateinit var tvError: TextView
    private lateinit var cvList: CardView
    private lateinit var rvList: RecyclerView
    private lateinit var adapter: TopAdapter
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvError = view.findViewById(R.id.tv_newsError)
        cvList = view.findViewById(R.id.cv_newsTopList)
        rvList = view.findViewById(R.id.rv_newsTopList)
        adapter = TopAdapter()
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(SharedViewModel::class.java)

        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(activity)
        rvList.adapter = adapter

        val parameterq = requireActivity().intent.getStringExtra("extra_search").toString()

        viewModel.setTop(parameterq)
        viewModel.getTop().observe(viewLifecycleOwner, {
            if(it.size == 0) {
                tvError.visibility = View.VISIBLE
                tvError.text = "Ups! Tidak ditemukan berita.\nMohon kembali lagi."
            } else {
                cvList.visibility = View.VISIBLE
                adapter.setList(it)
            }
        })

        viewModel.getErrorTop().observe(viewLifecycleOwner, {
            if(it != "") {
                tvError.visibility = View.VISIBLE
                tvError.text = it
            }
        })

        adapter.setOnItemClickCallback(object : TopAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Top) {
                showDetailNews(data)
            }
        })
    }

    private fun showDetailNews(top: Top) {
        Toast.makeText(activity, "Kamu memilih" + top.source.name, Toast.LENGTH_SHORT).show()
    }
}