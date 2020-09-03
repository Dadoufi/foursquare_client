package com.dadoufi.foursquare_client.ui.detail

import com.dadoufi.foursquare_client.data.local.entities.VenueDetailsEntity
import com.dadoufi.foursquare_client.ui.common.state.ViewState


sealed class DetailViewState(open val data: VenueDetailsEntity? = null) :
    ViewState<VenueDetailsEntity?> {
    data class DetailLoaded(override val data: VenueDetailsEntity? = null) : DetailViewState(data)
    data class DetailError(val message: String? = null) : DetailViewState()
    object Loading : DetailViewState()
}




