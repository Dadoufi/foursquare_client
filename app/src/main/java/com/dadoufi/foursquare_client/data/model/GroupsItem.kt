package com.dadoufi.foursquare_client.data.model

import com.google.gson.annotations.SerializedName

data class GroupsItem(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("items")
	val items: List<Any?>? = null
)