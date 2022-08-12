package com.schoters.gatotkaca.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.schoters.gatotkaca.R
import com.schoters.gatotkaca.data.Everything
import com.squareup.picasso.Picasso

class EverythingAdapter(private val list: ArrayList<Everything>): RecyclerView.Adapter<EverythingAdapter.EverythingViewHolder>() {
    inner class EverythingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(everything:Everything) {
            val tvTitle: TextView = itemView.findViewById(R.id.tv_newsTitle)
            val tvDescription: TextView = itemView.findViewById(R.id.tv_newsDescription)
            val ivImage: ImageView = itemView.findViewById(R.id.iv_newsImage)

            val title = everything.title
            val description = everything.description
            val image = everything.urlToImage

            tvTitle.text = title
            tvDescription.text = description
            Picasso.get()
                .load(image)
                .error(R.drawable.logo)
                .into(ivImage)

            itemView.startAnimation(AnimationUtils.loadAnimation(itemView.context, R.anim.list_animation))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EverythingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)
        return EverythingViewHolder(view)
    }

    override fun onBindViewHolder(holder: EverythingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}