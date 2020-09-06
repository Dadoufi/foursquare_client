package com.dadoufi.foursquare_client.search

import android.view.View
import com.agoda.kakao.progress.KProgressBar
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.text.KTextView
import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.ui.search.SearchFragment
import com.kaspersky.kaspresso.screens.KScreen
import kotlinx.coroutines.FlowPreview
import org.hamcrest.Matcher

@FlowPreview
object SearchScreen : KScreen<SearchScreen>() {
    override val layoutId: Int? = R.layout.fragment_search
    override val viewClass: Class<*>? = SearchFragment::class.java


    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.recyclerView)
    }, itemTypeBuilder = {
        itemType(::MainItem)
    })

    class MainItem(parent: Matcher<View>) : KRecyclerItem<MainItem>(parent) {
        val title: KTextView = KTextView(parent) { withId(R.id.venueTitle) }
        val subtitle: KTextView = KTextView(parent) { withId(R.id.venueLocation) }
    }

    class LoadingItem(parent: Matcher<View>) : KRecyclerItem<LoadingItem>(parent) {
        val title: KProgressBar = KProgressBar(parent) { withId(R.id.loading) }
    }

    class ErrorItem(parent: Matcher<View>) : KRecyclerItem<ErrorItem>(parent) {
        val title: KTextView = KTextView(parent) { withId(R.id.errorView) }
    }

}