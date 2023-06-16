package com.example.nutrimate.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("uid")
    val uid: String
//    @SerializedName("error")
//    val error: Boolean,
//    @SerializedName("message")
//    val message: String,
//    @SerializedName("loginResult")
//    val loginResult: LoginResult
)
