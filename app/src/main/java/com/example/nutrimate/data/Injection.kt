package com.example.nutrimate.data

import android.content.Context
import com.example.nutrimate.data.LoginPreferences
import com.example.nutrimate.data.LoginPreferences.Companion.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val loginPreferences = LoginPreferences(context.dataStore)
        return UserRepository(apiService,loginPreferences)
    }
}