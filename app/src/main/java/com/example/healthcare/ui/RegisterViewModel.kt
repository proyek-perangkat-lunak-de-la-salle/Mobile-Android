package com.example.healthcare.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthcare.data.UserRepository
import com.example.healthcare.data.remote.model.RegisterResponse
import com.example.healthcare.data.remote.model.UserRegister
import com.example.healthcare.helper.Result

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _registerResult = MutableLiveData<Result<RegisterResponse>>()
    val registerResult: LiveData<Result<RegisterResponse>> = _registerResult

    fun registerUser(user: UserRegister) {
        userRepository.register(user) {
            _registerResult.postValue(it)
        }
    }

}