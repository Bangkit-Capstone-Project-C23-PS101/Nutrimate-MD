package com.example.nutrimate.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nutrimate.R
import com.example.nutrimate.data.Food
import com.example.nutrimate.data.FoodDatabase

class HistoryAdapter(private val listFood: ArrayList<FoodDatabase>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_history, viewGroup, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = listFood[position]
        viewHolder.tvItem.text = item.name
        val url = item.image
        viewHolder.tvCalorie.text = item.calorie
        Glide.with(viewHolder.itemView.context).load(url).into(viewHolder.tvPhoto)
    }

    override fun getItemCount() = listFood.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tv_history_name)
        val tvPhoto: ImageView = view.findViewById(R.id.iv_history_photo)
        val tvCalorie: TextView = view.findViewById(R.id.tv_history_calorie)
    }
}