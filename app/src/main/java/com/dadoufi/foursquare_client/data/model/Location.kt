package com.dadoufi.foursquare_client.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(

    @field:SerializedName("cc")
    val cc: String,

    @field:SerializedName("country")
    val country: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("formattedAddress")
    val formattedAddress: List<String>,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("postalCode")
    val postalCode: String,

    @field:SerializedName("state")
    val state: String,

    @field:SerializedName("crossStreet")
    val crossStreet: String,

    @field:SerializedName("neighborhood")
    val neighborhood: String
) : Parcelable