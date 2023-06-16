package com.example.nutrimate.data

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("signup")
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field(encoded = true, value = "email") email: String,
        @Field("password") password: String
    ): LoginResponse
}