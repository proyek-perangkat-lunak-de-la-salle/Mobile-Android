package com.example.healthcare.data.remote.model

data class UserRegister(
    val nama: String? = null,
    val username: String? = null,
    val password: String? = null,
    val confirmPassword: String? = null,
    val email: String? = null,
    val role: String? = null,
    val age: Int = 0,
    val wilayah: Int = 0
)
