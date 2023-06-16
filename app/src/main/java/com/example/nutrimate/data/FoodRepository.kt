package com.example.nutrimate.data

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FoodRepository(application: Application) {
    private val mFoodsDao: FoodDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FoodRoomDatabase.getDatabase(application)
        mFoodsDao = db.FoodDao()
    }

    fun getAllFoods(): LiveData<List<FoodDatabase>> = mFoodsDao.getAllFoods()
    fun insert(Food: FoodDatabase) {
        executorService.execute { mFoodsDao.insert(Food) }
    }
}