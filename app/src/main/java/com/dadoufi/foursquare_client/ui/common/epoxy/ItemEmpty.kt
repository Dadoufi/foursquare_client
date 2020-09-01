package com.dadoufi.foursquare_client.ui.common.epoxy

import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.databinding.ItemEmptyBinding
import com.dadoufi.foursquare_client.utils.ViewBindingKotlinModel

data class ItemEmpty(val title: String?) :
    ViewBindingKotlinModel<ItemEmptyBinding>(R.layout.item_empty) {
    override fun ItemEmptyBinding.bind() {

    }
}