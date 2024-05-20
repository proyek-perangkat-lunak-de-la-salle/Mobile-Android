package com.example.healthcare.ui.general.fragment.riwayat

import androidx.lifecycle.ViewModel
import com.example.healthcare.data.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class RiwayatDeteksiViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getUserHistory() = run {
        val session = runBlocking {
            userRepository.getSession().first()
        }
        val userId = session.userId
        userRepository.getUserHistory(userId)
    }
}