package com.dadoufi.foursquare_client.db


import com.dadoufi.foursquare_client.data.local.daos.VenueDetailsDao
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class VenueDetailDaoTest : LocalDatabase() {

    private lateinit var dao: VenueDetailsDao

    @Before
    fun init() {
        dao = db.venueDetailsDao()
    }

    @Test
    fun `delete and store venue detail`() = runBlocking {

    }
}
