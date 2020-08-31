package com.dadoufi.foursquare_client.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VenuesEntity::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun venuesDao(): VenuesDao
}