package com.dadoufi.foursquare_client.data.model

import com.google.gson.annotations.SerializedName

data class Photos(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("groups")
	val groups: List<GroupsItem?>? = null
)