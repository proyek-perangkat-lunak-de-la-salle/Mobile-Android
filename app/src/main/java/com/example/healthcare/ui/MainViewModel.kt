package com.example.healthcare.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcare.data.UserRepository
import com.example.healthcare.data.pref.UserModel
import com.example.healthcare.data.remote.model.UserLogin
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun loginUser(user: UserLogin) = userRepository.loginUser(user)

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }
}