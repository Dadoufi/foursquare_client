package com.dadoufi.foursquare_client.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    @field:SerializedName("formattedAddress")
    val formattedAddress: List<String>,
) : Parcelable