package com.dadoufi.foursquare_client.ui.search

import com.airbnb.epoxy.TypedEpoxyController
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import com.dadoufi.foursquare_client.empty
import com.dadoufi.foursquare_client.error
import com.dadoufi.foursquare_client.loading
import com.dadoufi.foursquare_client.venue
import javax.inject.Inject


class SearchController @Inject constructor() :
    TypedEpoxyController<SearchViewState>() {
    var callbacks: Callbacks? = null


    init {
        isDebugLoggingEnabled = true
    }

    interface Callbacks {
        fun onItemClicked(item: VenuesEntity)
    }

    override fun buildModels(viewState: SearchViewState?) {
        when (viewState) {
            is SearchViewState.VenuesLoaded -> {
                if (viewState.data.isNullOrEmpty()) {
                    empty { id("empty") }
                }

                viewState.data?.forEach {
                    venue {
                        id(it.hashCode())
                        venue(it)
                        onClick { model, _, _, _ ->
                            callbacks?.onItemClicked(model.venue())
                        }
                    }
                }
            }
            is SearchViewState.VenuesLoadedError -> {
                error {
                    id("error")
                }
            }
            is SearchViewState.Loading -> {
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