package com.dadoufi.foursquare_client.ui.common.epoxy

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.EpoxyRecyclerView

class ClearEpoxyRecyclerView(context: Context, attrs: AttributeSet?) :
    EpoxyRecyclerView(context, attrs) {

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (adapter != null) {
            adapter = null
        }
    }
}