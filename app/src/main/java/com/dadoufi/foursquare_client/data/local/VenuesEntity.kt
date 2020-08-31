package com.dadoufi.foursquare_client.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "venues")
data class VenuesEntity(
    @PrimaryKey
    val venueId: String
)