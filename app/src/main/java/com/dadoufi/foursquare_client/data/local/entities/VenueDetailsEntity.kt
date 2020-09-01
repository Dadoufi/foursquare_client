package com.dadoufi.foursquare_client.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "venue_details")
class VenueDetailsEntity(
    @PrimaryKey
    val venueId: String
)