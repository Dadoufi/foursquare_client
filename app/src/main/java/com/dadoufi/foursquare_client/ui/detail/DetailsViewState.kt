package com.dadoufi.foursquare_client.ui.detail

import com.dadoufi.foursquare_client.data.local.entities.VenueDetailsEntity


sealed class DetailViewState {
    data class DetailLoaded(val data: VenueDetailsEntity? = null) : DetailViewState()
    data class DetailError(val message: String? = null) : DetailViewState()
    object Loading : DetailViewState()
}




