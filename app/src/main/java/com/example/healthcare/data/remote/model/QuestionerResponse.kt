package com.example.healthcare.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionerResponse(

	@field:SerializedName("predictedCluster")
	val predictedCluster: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("informasi")
	val informasi: String
) : Parcelable
