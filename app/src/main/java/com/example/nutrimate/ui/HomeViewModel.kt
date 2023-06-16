package com.example.nutrimate.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.nutrimate.data.Food
import com.example.nutrimate.data.FoodDatabase
import com.example.nutrimate.data.FoodRepository
import com.example.nutrimate.data.UserRepository

class HomeViewModel(application: Application): ViewModel() {
    private val mFoodRepository: FoodRepository = FoodRepository(application)
    fun insert(Food: FoodDatabase) {
        mFoodRepository.insert(Food)
    }
}