package com.example.nutrimate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class], version = 1)
abstract class FoodRoomDatabase : RoomDatabase() {
    abstract fun FoodDao(): FoodDao
    companion object {
        @Volatile
        private var INSTANCE: FoodRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): FoodRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FoodRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FoodRoomDatabase::class.java, "food_database")
                        .build()
                }
            }
            return INSTANCE as FoodRoomDatabase
        }
    }
}