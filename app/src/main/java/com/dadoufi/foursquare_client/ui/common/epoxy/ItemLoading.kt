package com.dadoufi.foursquare_client.ui.common.epoxy

import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.databinding.ItemLoadingBinding
import com.dadoufi.foursquare_client.utils.ViewBindingKotlinModel

data class ItemLoading(val title: String?) :
    ViewBindingKotlinModel<ItemLoadingBinding>(R.layout.item_loading) {
    override fun ItemLoadingBinding.bind() {

    }
}