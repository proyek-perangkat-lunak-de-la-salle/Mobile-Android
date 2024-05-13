package com.example.healthcare.data.di

import com.example.healthcare.data.UserRepository
import com.example.healthcare.data.remote.api.ApiConfig

object Injection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}