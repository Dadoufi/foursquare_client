package com.dadoufi.foursquare_client.data.model

import com.dadoufi.foursquare_client.data.local.entities.VenueDetailsEntity
import com.google.gson.annotations.SerializedName

data class VenueDetailResponse(

	@field:SerializedName("meta")
	val meta: Meta,

	@field:SerializedName("response")
	val response: DetailResponse
)

data class Venue(

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description")
	val description: String?,

	@field:SerializedName("photos")
	val photos: Photos,

	@field:SerializedName("contact")
	val contact: Contact,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("bestPhoto")
	val bestPhoto: BestPhoto?,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("location")
	val location: Location
)

data class DetailResponse(

	@field:SerializedName("venue")
	val venue: Venue
)

data class BestPhoto(

	@field:SerializedName("prefix")
	val prefix: String,

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("suffix")
	val suffix: String,

	@field:SerializedName("height")
	val height: Int


)

fun Venue.asVenueDetailsEntity(): VenueDetailsEntity =
	VenueDetailsEntity(
        venueId = id,
        title = name,
        description = description,
        address = location.formattedAddress,
        contactInfo = contact,
        rating = rating,
        image = bestPhoto.createUrl()

    )

fun BestPhoto?.createUrl(): String? {
	this?.let {
		return prefix.plus("cap").plus("720").plus(suffix)
	}
	return null
}

