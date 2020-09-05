package com.dadoufi.foursquare_client.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Contact(

	@field:SerializedName("twitter")
	val twitter: String? = null,

	@field:SerializedName("facebookUsername")
	val facebookUsername: String? = null,

	@field:SerializedName("formattedPhone")
	val formattedPhone: String? = null,

	@field:SerializedName("instagram")
	val instagram: String? = null,

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