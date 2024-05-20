package com.example.healthcare.data.remote.model

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("HistoryResponse")
	val historyResponse: List<HistoryResponseItem>
)

data class HistoryResponseItem(

	@field:SerializedName("cluster")
	val cluster: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("id_history")
	val idHistory: Int,

	@field:SerializedName("id_user")
	val idUser: Int,

	@field:SerializedName("timestamp")
	val timestamp: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
