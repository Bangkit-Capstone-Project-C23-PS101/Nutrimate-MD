package com.example.nutrimate.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(food: FoodDatabase)
    @Query("SELECT * from fooddatabase ORDER BY id ASC")
    fun getAllFoods(): LiveData<List<FoodDatabase>>
}