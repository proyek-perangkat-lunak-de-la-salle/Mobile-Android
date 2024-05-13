package com.example.healthcare.data.remote.api

import com.example.healthcare.data.remote.model.RegisterResponse
import com.example.healthcare.data.remote.model.UserRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    // Functions to interact with API

    @POST("users")
    fun registerUser(@Body requestBody: UserRegister): Call<RegisterResponse>

}