package com.example.nutrimate.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.nutrimate.data.Food
import com.example.nutrimate.data.FoodDatabase

class FoodDiffCallback(private val oldFoodList: List<FoodDatabase>, private val newFoodList: List<FoodDatabase>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldFoodList.size
    override fun getNewListSize(): Int = newFoodList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFoodList[oldItemPosition].id == newFoodList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFood = oldFoodList[oldItemPosition]
        val newFood = newFoodList[newItemPosition]
        return oldFood.name == newFood.name && oldFood.calorie == newFood.calorie
    }
}