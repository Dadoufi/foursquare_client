package com.dadoufi.foursquare_client.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dadoufi.foursquare_client.data.local.daos.VenueDetailsDao
import com.dadoufi.foursquare_client.data.local.daos.VenuesDao
import com.dadoufi.foursquare_client.data.local.entities.VenueDetailsEntity
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity

@Database(
    entities = [VenuesEntity::class, VenueDetailsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun venuesDao(): VenuesDao

    abstract fun venueDetailsDao(): VenueDetailsDao
}