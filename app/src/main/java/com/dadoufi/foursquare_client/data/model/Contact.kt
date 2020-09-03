package com.dadoufi.foursquare_client.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

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

enum class SocialType {
	FACEBOOK,
	INSTAGRAM,
	TWITTER;

	override fun toString(): String {
		return name.toLowerCase(Locale.ROOT)
	}
}

fun Contact.getSocialUrl(socialType: SocialType): String? {
	val socialUrl = "<a href=\"https://www.%s.com/%s\">%s</a>"
	val url: String?
	val socialName: String? = when (socialType) {
		SocialType.FACEBOOK -> {
			facebookUsername
		}
		SocialType.INSTAGRAM -> {
			instagram
		}
		SocialType.TWITTER -> {
			twitter
		}
	}
	url = String.format(socialUrl, socialType.toString(), socialName, socialName)

	return url
}