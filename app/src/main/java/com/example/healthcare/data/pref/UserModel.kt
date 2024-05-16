package com.example.healthcare.data.pref

data class UserModel(
    val token: String,
    val role: String,
    val userId: Int,
    val isLogin: Boolean = false
)
