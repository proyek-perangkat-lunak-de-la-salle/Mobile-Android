package com.example.healthcare.data

import android.util.Log
import com.example.healthcare.data.remote.api.ApiService
import com.example.healthcare.data.remote.model.RegisterResponse
import com.example.healthcare.data.remote.model.UserRegister
import com.example.healthcare.helper.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(val apiService: ApiService) {

    fun register(
        user: UserRegister,
        callback: (Result<RegisterResponse>) -> Unit
    ) {
        callback(Result.Loading)
        apiService.registerUser(user).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if(response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null) {
                        callback(Result.Success(responseBody))
                    } else {
                        callback(Result.Error("Registrasi gagal " + response.body()?.message.toString() + " " + response.message().toString()))
                        Log.d(TAG, "onResponse: response body is null")
                    }
                }
                else {
                    callback(Result.Error("Registrasi gagal"))
                    Log.d(TAG, "onResponse: response body is null " + response.body()?.message.toString() + " " + response.message().toString())
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                callback(Result.Error("Registrasi Gagal"))
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        @JvmStatic
        fun getInstance(apiService: ApiService): UserRepository {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = UserRepository(apiService)
                }
            }
            return INSTANCE as UserRepository
        }

        private const val TAG = "UserRepository"
    }
}