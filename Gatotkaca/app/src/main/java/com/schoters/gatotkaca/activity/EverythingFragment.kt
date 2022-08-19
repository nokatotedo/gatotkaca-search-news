package com.schoters.gatotkaca.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.schoters.gatotkaca.R
import com.schoters.gatotkaca.adapter.EverythingAdapter
import com.schoters.gatotkaca.data.Everything
import com.schoters.gatotkaca.data.EverythingDetail
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
            } else {
                adapter.setList(it)
            }
        })

        viewModel.getErrorEverything().observe(viewLifecycleOwner, {
            if(it != "") {
                tvError.visibility = View.VISIBLE
                tvError.text = it
            }
        })

        adapter.setOnItemClickCallback(object : EverythingAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Everything) {
                val urlToImage = data.urlToImage
                val title = data.title
                val description = data.description
                val author = data.author
                val source = data.source.name
                val published = data.publishedAt
                val detailNews = EverythingDetail(
                    source,
                    title,
                    author,
                    description,
                    urlToImage,
                    published
                )

                val action = EverythingFragmentDirections.actionEverythingFragmentToEverythingDetailFragment(detailNews)
                findNavController().navigate(action)
            }
        })
    }
}