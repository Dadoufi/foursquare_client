package com.dadoufi.foursquare_client.ui.detail

import com.airbnb.epoxy.TypedEpoxyController
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import com.dadoufi.foursquare_client.empty
import com.dadoufi.foursquare_client.error
import com.dadoufi.foursquare_client.loading
import javax.inject.Inject


class DetailController @Inject constructor() :
    TypedEpoxyController<DetailViewState>() {
    var callbacks: Callbacks? = null


    init {
        isDebugLoggingEnabled = true
    }

    interface Callbacks {
        fun onItemClicked(item: VenuesEntity)
    }

    override fun buildModels(viewState: DetailViewState?) {
        when (viewState) {
            is DetailViewState.DetailLoaded -> {
                if (viewState.data == null) {
                    empty {
                        id("empty")
                    }
                }

                viewState.data?.let {

                }
            }
            is DetailViewState.DetailError -> {
                error {
                    id("error")
                }
            }
            is DetailViewState.Loading -> {
                loading {
                    id("loading")
                }
            }
        }
    }

    override fun onExceptionSwallowed(exception: RuntimeException) {
        throw exception
    }


}