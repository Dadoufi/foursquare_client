package com.dadoufi.foursquare_client.data.local

import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
class LocalDataSource @Inject constructor(private val venuesDao: VenuesDao) {
}