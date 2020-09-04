package com.dadoufi.foursquare_client.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meta(
    @field:SerializedName("code")
    val code: Int,
    @field:SerializedName("requestId")
    val requestId: String,
    @field:SerializedName("errorType")
    val errorType: String? = null,
    @field:SerializedName("errorDetail")
    val errorDetail: String? = null
) : Parcelable