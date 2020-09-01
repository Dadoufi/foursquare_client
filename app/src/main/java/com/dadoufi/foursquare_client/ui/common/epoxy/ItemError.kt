package com.dadoufi.foursquare_client.ui.common.epoxy

import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.databinding.ItemErrorBinding
import com.dadoufi.foursquare_client.utils.ViewBindingKotlinModel

data class ItemError(val title: String?) :
    ViewBindingKotlinModel<ItemErrorBinding>(R.layout.item_error) {
    override fun ItemErrorBinding.bind() {

    }
}