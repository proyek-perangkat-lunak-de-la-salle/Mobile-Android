package com.example.healthcare.data.pref

data class UserModel(
    val token: String,
    val role: String,
    val isLogin: Boolean = false
)
