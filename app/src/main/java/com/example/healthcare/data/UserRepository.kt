package com.example.healthcare.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthcare.data.pref.UserModel
import com.example.healthcare.data.pref.UserPref
import com.example.healthcare.data.remote.api.ApiService
import com.example.healthcare.data.remote.model.HistoryResponseItem
import com.example.healthcare.data.remote.model.LoginResponse
import com.example.healthcare.data.remote.model.Questioner
import com.example.healthcare.data.remote.model.QuestionerResponse
import com.example.healthcare.data.remote.model.RegisterResponse
import com.example.healthcare.data.remote.model.UserLogin
import com.example.healthcare.data.remote.model.UserRegister
import com.example.healthcare.helper.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(val apiService: ApiService, val userPref: UserPref) {

    val result = MutableLiveData<Result<RegisterResponse>>()

    val resultLogin = MutableLiveData<Result<LoginResponse>>()

    val resultFormGeneral = MutableLiveData<Result<QuestionerResponse>>()

    val resultHistory = MutableLiveData<Result<List<HistoryResponseItem>>>()

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
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        return result
    }

    fun loginUser(user: UserLogin): LiveData<Result<LoginResponse>> {
        resultLogin.value = Result.Loading
        val client = apiService.login(user)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        resultLogin.value = Result.Success(response.body()!!)
                        Log.d(TAG, "onResponse: ${response.body()!!.role}")
                    }
                } else {
                    resultLogin.value = Result.Error(response.message())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                resultLogin.value = Result.Error(t.message.toString())
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return resultLogin
    }

    suspend fun saveSession(user: UserModel) {
        userPref.saveSession(user)
    }

    fun getSession(): Flow<UserModel> = userPref.getSession()

    suspend fun logout() {
        userPref.logout()
    }

    fun submitForm(token: String, form: Questioner): LiveData<Result<QuestionerResponse>> {
        resultFormGeneral.value = Result.Loading
        val client = apiService.submitQuestioners("Bearer $token", form)

        client.enqueue(object : Callback<QuestionerResponse> {
            override fun onResponse(
                call: Call<QuestionerResponse>,
                response: Response<QuestionerResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        resultFormGeneral.value = Result.Success(response.body()!!)
                    } else {
                        Log.d(TAG, "onResponse: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<QuestionerResponse>, t: Throwable) {
                resultFormGeneral.value = Result.Error(t.message.toString())
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return resultFormGeneral
    }

    fun getUserHistory(userId: Int): LiveData<Result<List<HistoryResponseItem>>> {
        resultHistory.value = Result.Loading
        val client = apiService.getHistory(userId)

        client.enqueue(object: Callback<List<HistoryResponseItem>>{
            override fun onResponse(
                call: Call<List<HistoryResponseItem>>,
                response: Response<List<HistoryResponseItem>>
            ) {
                if(response.isSuccessful) {
                    if(response.body() != null) {
                        resultHistory.value = Result.Success(response.body()!!)
                    } else {
                        Log.d(TAG, "onResponse: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<HistoryResponseItem>>, t: Throwable) {
                resultHistory.value = Result.Error("${t.message}")
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })

        return resultHistory
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        @JvmStatic
        fun getInstance(apiService: ApiService, userPref: UserPref): UserRepository {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = UserRepository(apiService, userPref)
                }
            }
            return INSTANCE as UserRepository
        }

        private const val TAG = "UserRepository"
    }
}