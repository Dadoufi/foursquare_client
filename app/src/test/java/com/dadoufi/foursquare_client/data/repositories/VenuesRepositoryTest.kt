package com.dadoufi.foursquare_client.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.dadoufi.foursquare_client.MainCoroutinesRule
import com.dadoufi.foursquare_client.core.ResultWrapper
import com.dadoufi.foursquare_client.data.local.LocalDataSource
import com.dadoufi.foursquare_client.data.model.*
import com.dadoufi.foursquare_client.data.remote.NetworkDataSource
import com.dadoufi.foursquare_client.utils.ConnectivityStateManager
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VenuesRepositoryTest {

    private lateinit var repository: VenuesRepository
    private val networkDataSource: NetworkDataSource = mock()
    private val localDataSource: LocalDataSource = mock()
    private val connectivityStateManager: ConnectivityStateManager = mock()

    private lateinit var scope: TestCoroutineScope


    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository =
            VenuesRepositoryImpl(networkDataSource, localDataSource, connectivityStateManager)
    }

    @Test
    fun `test fetching venues from network success`() = runBlocking {
        val mockData = VenueSearchResponse(
            meta = Meta(200, "someId"),
            response = Response(
                true,
                mutableListOf(
                    VenuesItem(
                        name = "Cool venue",
                        location = Location(mutableListOf("address1", "address2", "address3")),
                        id = "id1"
                    ),
                    VenuesItem(
                        name = "Cooler venue",
                        location = Location(mutableListOf("address3", "address2", "address1")),
                        id = "id2"
                    )
                )
            )
        )

        val query = "query"
        val mockEntities = mockData.response.venues.asVenuesEntity(query)

        whenever(connectivityStateManager.isConnected()).doReturn(true)
        val flowResponse = flow {
            emit(mockData)
        }

        whenever(networkDataSource.getVenues(query)).doReturn(flowResponse)
        whenever(localDataSource.getStoredVenues(query)).doReturn(ResultWrapper.Success(mockEntities))

        repository.getVenues(query).test {
            assertEquals(expectItem(), ResultWrapper.Success(mockEntities))
        }

        verify(networkDataSource).getVenues(query)
        verify(localDataSource).deleteAndStoreVenues(eq(query), eq(mockEntities))
    }
}