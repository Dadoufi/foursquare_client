package com.dadoufi.foursquare_client.ui.search

import com.airbnb.epoxy.TypedEpoxyController
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import com.dadoufi.foursquare_client.empty
import com.dadoufi.foursquare_client.loading
import com.dadoufi.foursquare_client.venue
import javax.inject.Inject


class SearchController @Inject constructor() :
    TypedEpoxyController<List<VenuesEntity>>() {
    var callbacks: Callbacks? = null

    var isLoading = false
        set(value) {
            if (value != field) {
                field = value
                requestDelayedModelBuild(500)
            }
        }

    fun toggleLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    var isError = false
        set(value) {
            if (value != field) {
                field = value
                requestDelayedModelBuild(500)
            }
        }


    init {
        isDebugLoggingEnabled = true
    }

    override fun buildModels(data: List<VenuesEntity>?) {
        if (isLoading) {
            loading {
                id("loading")
            }
        }

        if (data.isNullOrEmpty()) {
            empty { id("empty") }
        }

        data?.forEach {
            venue {
                id(it.hashCode())
                venue(it)
                onClick { model, _, _, _ ->
                    callbacks?.onItemClicked(model.venue())
                }
            }
        }
    }

    override fun onExceptionSwallowed(exception: RuntimeException) {
        throw exception
    }


    interface Callbacks {
        fun onItemClicked(item: VenuesEntity)
    }


}