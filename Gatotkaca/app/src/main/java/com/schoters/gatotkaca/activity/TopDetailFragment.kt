package com.schoters.gatotkaca.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.button.MaterialButton
import com.schoters.gatotkaca.R
import com.squareup.picasso.Picasso

class TopDetailFragment : Fragment() {
    private lateinit var mbClose: MaterialButton
    private lateinit var ivImage: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvAuthor: TextView
    private lateinit var tvSource: TextView
    private lateinit var tvPublishedAt: TextView
    private val args by navArgs<TopDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mbClose = view.findViewById(R.id.mb_close)
        ivImage = view.findViewById(R.id.iv_image)
        tvTitle = view.findViewById(R.id.tv_title)
        tvDescription = view.findViewById(R.id.tv_description)
        tvAuthor = view.findViewById(R.id.tv_author)
        tvSource = view.findViewById(R.id.tv_source)
        tvPublishedAt = view.findViewById(R.id.tv_published)

        Picasso.get()
            .load(args.detailNews.urlToImage)
            .resize(1920,1080)
            .error(R.drawable.header)
            .into(ivImage)
        tvTitle.text = args.detailNews.title
        tvDescription.text = args.detailNews.description
        tvAuthor.text = args.detailNews.author
        tvSource.text = args.detailNews.source
        tvPublishedAt.text = args.detailNews.publishedAt

        mbClose.setOnClickListener{
            val action = TopDetailFragmentDirections.actionTopDetailFragmentToTopFragment()
            findNavController().navigate(action)
        }
    }
}