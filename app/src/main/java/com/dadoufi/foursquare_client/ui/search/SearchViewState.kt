package com.dadoufi.foursquare_client.ui.search

import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity


sealed class SearchViewState {
    data class VenuesLoaded(val data: List<VenuesEntity>? = null) : SearchViewState()
    data class VenuesLoadedError(val message: String? = null) : SearchViewState()
    object Loading : SearchViewState()
}




