package com.example.healthcare.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthcare.data.remote.api.ApiService
import com.example.healthcare.data.remote.model.RegisterResponse
import com.example.healthcare.data.remote.model.UserRegister
import com.example.healthcare.helper.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(val apiService: ApiService) {

    val result = MutableLiveData<Result<RegisterResponse>>()

    fun registerUser(user: UserRegister): LiveData<Result<RegisterResponse>> {
        result.value = Result.Loading
        val client = apiService.registerUser(user)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body()!!)
                } else {
                    result.value = Result.Error("A user with this email already exists")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }

        })
        return result
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