package com.dadoufi.foursquare_client.data.model

import com.google.gson.annotations.SerializedName

data class Contact(

	@field:SerializedName("twitter")
	val twitter: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("facebookUsername")
	val facebookUsername: String,

	@field:SerializedName("facebook")
	val facebook: String,

	@field:SerializedName("formattedPhone")
	val formattedPhone: String,

	@field:SerializedName("instagram")
	val instagram: String,

	@field:SerializedName("facebookName")
	val facebookName: String? = null
)