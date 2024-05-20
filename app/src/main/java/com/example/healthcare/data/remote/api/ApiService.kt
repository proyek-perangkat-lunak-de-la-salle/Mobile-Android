package com.example.healthcare.data.remote.api

import com.example.healthcare.data.remote.model.HistoryResponseItem
import com.example.healthcare.data.remote.model.LoginResponse
import com.example.healthcare.data.remote.model.Questioner
import com.example.healthcare.data.remote.model.QuestionerResponse
import com.example.healthcare.data.remote.model.RegisterResponse
import com.example.healthcare.data.remote.model.UserLogin
import com.example.healthcare.data.remote.model.UserRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("users")
    fun registerUser(@Body requestBody: UserRegister): Call<RegisterResponse>

    @POST("login")
    fun login(@Body requestBody: UserLogin) : Call<LoginResponse>

    @POST("kuesioners")
    fun submitQuestioners(@Header("Authorization") token: String, @Body requestBody: Questioner) : Call<QuestionerResponse>

    @GET("history/{userId}")
    fun getHistory(@Path("userId")userId: Int) : Call<List<HistoryResponseItem>>

}