package com.schoters.gatotkaca.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.schoters.gatotkaca.R

class EverythingDetailFragment : Fragment() {
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private val args by navArgs<EverythingDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_everything_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitle = view.findViewById(R.id.tv_title)
        tvDescription = view.findViewById(R.id.tv_description)

        tvTitle.text = "${args.detailNews.title}"
        tvDescription.text = "${args.detailNews.description}"
    }
}