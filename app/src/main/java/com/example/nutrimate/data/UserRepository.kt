package com.example.nutrimate.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

class UserRepository(private val apiService: ApiService) {
    fun postRegister(name: String, email: String, password: String): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.registerUser(name, email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("RegisterViewModel", "register: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }
    fun postLogin(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.loginUser(email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("LoginViewModel", "login: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }
}