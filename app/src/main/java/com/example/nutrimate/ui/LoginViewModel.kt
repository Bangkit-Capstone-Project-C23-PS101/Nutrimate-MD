package com.example.nutrimate.ui

import androidx.lifecycle.ViewModel
import com.example.nutrimate.data.UserRepository

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {
    fun login(email: String, password: String) = userRepository.postLogin(email,password)
    fun isLogin() = userRepository.isLogin()
}