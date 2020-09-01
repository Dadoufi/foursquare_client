package com.dadoufi.foursquare_client.data.model

import android.os.Parcelable
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VenueSearchResponse(

	@field:SerializedName("meta")
	val meta: Meta,

	@field:SerializedName("response")
	val response: Response
) : Parcelable

@Parcelize
data class VenuesItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("location")
	val location: Location,

	@field:SerializedName("id")
	val id: String,
) : Parcelable

@Parcelize
data class Response(

	@field:SerializedName("confident")
	val confident: Boolean,

	@field:SerializedName("venues")
	val venues: List<VenuesItem>
) : Parcelable


fun List<VenuesItem>.asVenuesEntity(query: String): List<VenuesEntity> = this.map {
    VenuesEntity(it.id, it.name, it.location.formattedAddress, query)
}
