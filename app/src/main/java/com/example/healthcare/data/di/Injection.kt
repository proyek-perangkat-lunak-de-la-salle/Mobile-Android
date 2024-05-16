package com.example.healthcare.data.di

import android.content.Context
import com.example.healthcare.data.UserRepository
import com.example.healthcare.data.pref.UserPref
import com.example.healthcare.data.pref.dataStore
import com.example.healthcare.data.remote.api.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val pref = context.dataStore
        val userPref = UserPref.getInstance(pref)
        return UserRepository.getInstance(apiService, userPref)
    }
}
