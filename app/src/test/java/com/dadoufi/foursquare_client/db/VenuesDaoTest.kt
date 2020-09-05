package com.dadoufi.foursquare_client.db

import com.dadoufi.foursquare_client.data.local.daos.VenuesDao
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class VenuesDaoTest : LocalDatabase() {

    private lateinit var dao: VenuesDao

    @Before
    fun init() {
        dao = db.venuesDao()
    }

    @Test
    fun `delete and insert venue list`() = runBlocking {

    }
}
