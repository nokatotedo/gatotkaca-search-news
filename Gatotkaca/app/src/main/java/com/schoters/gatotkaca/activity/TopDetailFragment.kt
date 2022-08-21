package com.schoters.gatotkaca.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.button.MaterialButton
import com.schoters.gatotkaca.R
import com.schoters.gatotkaca.database.DatabaseViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopDetailFragment : Fragment() {
    private lateinit var tbFavorite: ToggleButton
    private lateinit var mbClose: MaterialButton
    private lateinit var ivImage: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvAuthor: TextView
    private lateinit var tvSource: TextView
    private lateinit var tvPublishedAt: TextView
    private lateinit var viewModel: DatabaseViewModel
    private val args by navArgs<TopDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tbFavorite = view.findViewById(R.id.tb_favorite)
        mbClose = view.findViewById(R.id.mb_close)
        ivImage = view.findViewById(R.id.iv_image)
        tvTitle = view.findViewById(R.id.tv_title)
        tvDescription = view.findViewById(R.id.tv_description)
        tvAuthor = view.findViewById(R.id.tv_author)
        tvSource = view.findViewById(R.id.tv_source)
        tvPublishedAt = view.findViewById(R.id.tv_published)

        viewModel = ViewModelProvider(requireActivity()).get(DatabaseViewModel::class.java)

        val urlToImage = args.detailNews.urlToImage
        val title = args.detailNews.title
        val description = args.detailNews.description
        val author = args.detailNews.author
        val source = args.detailNews.source
        val publishedAt = args.detailNews.publishedAt

        var isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkNews(title)
            withContext(Dispatchers.Main) {
                if(count != null) {
                    if(count > 0) {
                        tbFavorite.isChecked = true
                        isChecked = true
                    } else {
                        tbFavorite.isChecked = false
                        isChecked = false
                    }
                }
            }
        }

        Picasso.get()
            .load(urlToImage)
            .resize(1920,1080)
            .error(R.drawable.header)
            .into(ivImage)
        tvTitle.text = title
        tvDescription.text = description
        tvAuthor.text = author
        tvSource.text = source
        tvPublishedAt.text = publishedAt

        mbClose.setOnClickListener {
            val action = TopDetailFragmentDirections.actionTopDetailFragmentToTopFragment()
            findNavController().navigate(action)
        }

        tbFavorite.setOnClickListener {
            isChecked = !isChecked
            if(isChecked) {
                viewModel.insertNews(
                    title,
                    source,
                    author,
                    description,
                    urlToImage,
                    publishedAt
                )
            } else {
                viewModel.removeNews(title)
            }
            tbFavorite.isChecked = isChecked
        }
    }
}