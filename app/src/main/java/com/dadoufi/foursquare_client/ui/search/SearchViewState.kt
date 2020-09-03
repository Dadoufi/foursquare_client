package com.dadoufi.foursquare_client.ui.search

import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import com.dadoufi.foursquare_client.ui.common.state.ViewState


sealed class SearchViewState(open val data: List<VenuesEntity>? = null) :
    ViewState<List<VenuesEntity>?> {

    data class VenuesLoaded(override val data: List<VenuesEntity>? = null) : SearchViewState(data)
    data class VenuesLoadedError(val message: String? = null) : SearchViewState()
    object Loading : SearchViewState()
}




