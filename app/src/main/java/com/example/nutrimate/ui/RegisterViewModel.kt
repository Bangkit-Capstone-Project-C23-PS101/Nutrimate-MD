package com.example.nutrimate.ui

import androidx.lifecycle.ViewModel
import com.example.nutrimate.data.UserRepository

class RegisterViewModel(private val userRepository: UserRepository): ViewModel() {
    fun register(email: String, password: String) = userRepository.postRegister(email,password)
}