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
import com.schoters.gatotkaca.data.EverythingSource
import com.schoters.gatotkaca.database.DatabaseViewModel
import com.schoters.gatotkaca.database.FavoriteDetail
import com.schoters.gatotkaca.database.News

class FavoriteFragment : Fragment() {
    private lateinit var tvError: TextView
    private lateinit var rvList: RecyclerView
    private lateinit var adapter: EverythingAdapter
    private lateinit var viewModel: DatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvError = view.findViewById(R.id.tv_newsError)
        rvList = view.findViewById(R.id.rv_newsFavoriteList)
        adapter = EverythingAdapter()
        viewModel = ViewModelProvider(requireActivity()).get(DatabaseViewModel::class.java)

        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(activity)
        rvList.adapter = adapter

        viewModel.getNews().observe(viewLifecycleOwner, {
            if(it != null) {
                val list = mapList(it)
                if(list.size == 0) {
                    tvError.visibility = View.VISIBLE
                    tvError.text = "Tidak ada berita favorit."
                }
                adapter.setList(list)
            }
        })

        adapter.setOnItemClickCallback(object : EverythingAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Everything) {
                var urlToImage = "null"
                val title = data.title
                val description = data.description
                var author = "Anonymous"
                var source = "Anonymous"
                val published = data.publishedAt

                if(data.urlToImage != null) {
                    urlToImage = data.urlToImage
                }
                if(data.author != null) {
                    author = data.author
                }
                if(data.source.name != null) {
                    source = data.source.name
                }

                val detailNews = FavoriteDetail(
                    source,
                    title,
                    author,
                    description,
                    urlToImage,
                    published
                )

                val action = FavoriteFragmentDirections.actionFavoriteFragmentToFavoriteDetailFragment(detailNews)
                findNavController().navigate(action)
            }
        })
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