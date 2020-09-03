package com.dadoufi.foursquare_client.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dadoufi.foursquare_client.data.model.Contact

@Entity(tableName = "venue_details")
class VenueDetailsEntity(
    @PrimaryKey
    val venueId: String,
    val title: String,
    val description: String,
    val address: List<String>,
    val contactInfo: Contact,
    val rating: Double,
    val image: String?
)