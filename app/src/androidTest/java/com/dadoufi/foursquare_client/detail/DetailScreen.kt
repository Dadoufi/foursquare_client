package com.dadoufi.foursquare_client.detail

import com.agoda.kakao.text.KTextView
import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.ui.detail.DetailFragment
import com.kaspersky.kaspresso.screens.KScreen
import kotlinx.coroutines.FlowPreview

@FlowPreview
object DetailScreen : KScreen<DetailScreen>() {
    override val layoutId: Int? = R.layout.fragment_detail
    override val viewClass: Class<*>? = DetailFragment::class.java


    val description: KTextView = KTextView {
        withId(R.id.description)
    }

    val rating: KTextView = KTextView {
        withId(R.id.rating)
    }

    val address: KTextView = KTextView {
        withId(R.id.address)
    }

    val phone: KTextView = KTextView {
        withId(R.id.phone)
    }

    val facebook: KTextView = KTextView {
        withId(R.id.facebook)
    }

    val instagram: KTextView = KTextView {
        withId(R.id.instagram)
    }

    val twitter: KTextView = KTextView {
        withId(R.id.twitter)
    }


}