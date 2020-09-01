package com.dadoufi.foursquare_client.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "venues")
data class VenuesEntity(
    @PrimaryKey
    val venueId: String,
    val name: String,
    val location: List<String>,
    val query: String
)