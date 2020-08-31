package com.dadoufi.foursquare_client.data.model

import android.os.Parcelable
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
data class Geocode(

	@field:SerializedName("what")
	val what: String,

	@field:SerializedName("feature")
	val feature: Feature,

	@field:SerializedName("where")
	val where: String,

//	@field:SerializedName("parents")
//	val parents: List<Any>
) : Parcelable

@Parcelize
data class CategoriesItem(

	@field:SerializedName("pluralName")
	val pluralName: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("icon")
	val icon: Icon,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("shortName")
	val shortName: String,

	@field:SerializedName("primary")
	val primary: Boolean
) : Parcelable

@Parcelize
data class Bounds(

	@field:SerializedName("sw")
	val sw: Sw,

	@field:SerializedName("ne")
	val ne: Ne
) : Parcelable

@Parcelize
data class Geometry(

	@field:SerializedName("center")
	val center: Center,

	@field:SerializedName("bounds")
	val bounds: Bounds
) : Parcelable

@Parcelize
data class VenuesItem(

	@field:SerializedName("hasPerk")
	val hasPerk: Boolean,

	@field:SerializedName("hereNow")
	val hereNow: HereNow,

	@field:SerializedName("stats")
	val stats: Stats,

	@field:SerializedName("referralId")
	val referralId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("verified")
	val verified: Boolean,

	@field:SerializedName("location")
	val location: Location,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("categories")
	val categories: List<CategoriesItem>,

	@field:SerializedName("beenHere")
	val beenHere: BeenHere,

	@field:SerializedName("venuePage")
	val venuePage: VenuePage
) : Parcelable

@Parcelize
data class BeenHere(

	@field:SerializedName("marked")
	val marked: Boolean,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("lastCheckinExpiredAt")
	val lastCheckinExpiredAt: Int,

	@field:SerializedName("unconfirmedCount")
	val unconfirmedCount: Int
) : Parcelable

@Parcelize
data class Center(

	@field:SerializedName("lng")
	val lng: Double,

	@field:SerializedName("lat")
	val lat: Double
) : Parcelable

@Parcelize
data class Feature(

	@field:SerializedName("cc")
	val cc: String,

	@field:SerializedName("woeType")
	val woeType: Int,

	@field:SerializedName("highlightedName")
	val highlightedName: String,

	@field:SerializedName("displayName")
	val displayName: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("geometry")
	val geometry: Geometry,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("longId")
	val longId: String,

	@field:SerializedName("matchedName")
	val matchedName: String,

	@field:SerializedName("slug")
	val slug: String
) : Parcelable

@Parcelize
data class Sw(

	@field:SerializedName("lng")
	val lng: Double,

	@field:SerializedName("lat")
	val lat: Double
) : Parcelable

@Parcelize
data class Icon(

	@field:SerializedName("prefix")
	val prefix: String,

	@field:SerializedName("suffix")
	val suffix: String
) : Parcelable

@Parcelize
data class VenuePage(

	@field:SerializedName("id")
	val id: String
) : Parcelable

@Parcelize
data class Ne(

	@field:SerializedName("lng")
	val lng: Double,

	@field:SerializedName("lat")
	val lat: Double
) : Parcelable

@Parcelize
data class Stats(

	@field:SerializedName("visitsCount")
	val visitsCount: Int,

	@field:SerializedName("checkinsCount")
	val checkinsCount: Int,

	@field:SerializedName("usersCount")
	val usersCount: Int,

	@field:SerializedName("tipCount")
	val tipCount: Int
) : Parcelable

@Parcelize
data class Response(

	@field:SerializedName("confident")
	val confident: Boolean,

	@field:SerializedName("geocode")
	val geocode: Geocode,

	@field:SerializedName("venues")
	val venues: List<VenuesItem>
) : Parcelable

@Parcelize
data class HereNow(

	@field:SerializedName("summary")
	val summary: String,

	@field:SerializedName("count")
	val count: Int,
) : Parcelable

@Parcelize
data class Meta(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("requestId")
	val requestId: String
) : Parcelable

@Parcelize
data class Location(

	@field:SerializedName("cc")
	val cc: String,

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("labeledLatLngs")
	val labeledLatLngs: List<LabeledLatLngsItem>,

	@field:SerializedName("lng")
	val lng: Double,

	@field:SerializedName("formattedAddress")
	val formattedAddress: List<String>,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("postalCode")
	val postalCode: String,

	@field:SerializedName("state")
	val state: String,

	@field:SerializedName("lat")
	val lat: Double,

	@field:SerializedName("crossStreet")
	val crossStreet: String,

	@field:SerializedName("neighborhood")
	val neighborhood: String
) : Parcelable

@Parcelize
data class LabeledLatLngsItem(

	@field:SerializedName("lng")
	val lng: Double,

	@field:SerializedName("label")
	val label: String,

	@field:SerializedName("lat")
	val lat: Double
) : Parcelable
