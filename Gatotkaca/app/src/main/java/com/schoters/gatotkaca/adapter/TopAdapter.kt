package com.schoters.gatotkaca.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.schoters.gatotkaca.R
import com.schoters.gatotkaca.data.Top

class TopAdapter: RecyclerView.Adapter<TopAdapter.TopViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val list = ArrayList<Top>()
    private val number = (1..10).toList().toTypedArray()

    fun setList(top: ArrayList<Top>) {
        list.clear()
        list.addAll(top)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

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
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(list[holder.bindingAdapterPosition]) }
    }

    override fun getItemCount(): Int {
        if(list.size >= 10) {
            return 10
        }
        return list.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Top)
    }
}