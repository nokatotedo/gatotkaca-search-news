package com.schoters.gatotkaca.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.schoters.gatotkaca.R
import com.schoters.gatotkaca.data.Top

class TopAdapter(private val list: ArrayList<Top>): RecyclerView.Adapter<TopAdapter.TopViewHolder>(){
    private val number = (1..10).toList().toTypedArray()
    inner class TopViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNumber: TextView = itemView.findViewById(R.id.tv_newsNumber)
        fun bind(top: Top) {
            val tvTitle: TextView = itemView.findViewById(R.id.tv_newsTitle)
            val title = top.title
            tvTitle.text = title

            itemView.startAnimation(AnimationUtils.loadAnimation(itemView.context, R.anim.top_animation))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_list,parent,false)
        return TopViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.bind(list[position])
        val num = number[position].toString()
        val numstring = "#${num}"
        holder.tvNumber.text = numstring
    }

    override fun getItemCount(): Int = 10
}