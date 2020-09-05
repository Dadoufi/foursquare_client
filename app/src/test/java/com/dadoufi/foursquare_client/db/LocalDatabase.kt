package com.dadoufi.foursquare_client.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.dadoufi.foursquare_client.data.local.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
abstract class LocalDatabase {
  lateinit var db: AppDatabase

  @Before
  fun initDB() {
    db = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase::class.java)
      .allowMainThreadQueries()
      .build()
  }

  @After
  fun closeDB() {
    db.close()
  }
}
