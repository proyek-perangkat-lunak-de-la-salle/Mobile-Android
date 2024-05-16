package com.example.healthcare.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Questioner(
    val id_user: Int,
    val jenis_kelamin: String,
    val tinggi_badan: Int,
    val berat_badan: Int,
    val paham_pjk: String,
    val checkup_rutin: String,
    val nyeri_dada: String,
    val mual: String,
    val sesak_napas: String,
    val nyeri_uluhati: String,
    val hipertensi: String,
    val obesitas: String,
    val diabetes: String,
    val genetika: String
) : Parcelable
