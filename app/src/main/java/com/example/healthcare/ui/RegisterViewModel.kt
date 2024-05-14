package com.example.healthcare.ui

import androidx.lifecycle.ViewModel
import com.example.healthcare.data.UserRepository
import com.example.healthcare.data.remote.model.UserRegister

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun registerUser(user: UserRegister) = userRepository.registerUser(user)

}