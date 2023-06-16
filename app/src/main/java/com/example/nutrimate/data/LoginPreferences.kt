package com.example.nutrimate.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class LoginPreferences(private val dataStore: DataStore<Preferences>) {
    suspend fun getToken(): String {
        val preferences = dataStore.data.first()
        return preferences[TOKEN_KEY] ?: ""
    }
    suspend fun getStatus(): Boolean{
        val preferences = dataStore.data.first()
        return preferences[STATUS_KEY] ?: false
    }
    suspend fun setToken(token: String){
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[STATUS_KEY] = true
        }
    }

    suspend fun logout(){
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = ""
            preferences[STATUS_KEY] = false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: LoginPreferences? = null
        private val STATUS_KEY = booleanPreferencesKey("status")
        private val TOKEN_KEY = stringPreferencesKey("token")
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("nutrimate")

        fun getInstance(dataStore: DataStore<Preferences>): LoginPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = LoginPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}