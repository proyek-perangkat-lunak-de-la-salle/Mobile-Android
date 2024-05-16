package com.example.healthcare.ui.general.fragment.kuesioner

import androidx.lifecycle.ViewModel
import com.example.healthcare.data.UserRepository
import com.example.healthcare.data.remote.model.Questioner
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class QuestionerViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun submitForm(
        jenisKelamin: String,
        tinggi: Int,
        berat: Int,
        pahamPJK: String,
        checkupRutin: String,
        nyeriDada: String,
        mual: String,
        sesakNapas: String,
        nyeriUluHati: String,
        hipertensi: String,
        obesitas: String,
        diabetes: String,
        genetika: String
    ) = run {
        val session = runBlocking {
            userRepository.getSession().first()
        }
        val token = session.token
        val userId = session.userId
        val form = Questioner(
            userId,
            jenisKelamin,
            tinggi,
            berat,
            pahamPJK,
            checkupRutin,
            nyeriDada,
            mual,
            sesakNapas,
            nyeriUluHati,
            hipertensi, obesitas, diabetes, genetika
        )

        userRepository.submitForm(token, form)
    }
}