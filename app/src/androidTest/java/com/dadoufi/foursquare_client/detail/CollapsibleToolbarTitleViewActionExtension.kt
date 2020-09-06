package com.dadoufi.foursquare_client.detail

import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.hamcrest.Description
import org.hamcrest.Matcher


class CollapsibleToolbarTitleViewActionExtension {

    companion object {
        fun withCollapsibleToolbarTitle(textMatcher: Matcher<String?>): Matcher<Any?>? {
            return object :
                BoundedMatcher<Any?, CollapsingToolbarLayout>(CollapsingToolbarLayout::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("with toolbar title: ")
                    textMatcher.describeTo(description)
                }

                override fun matchesSafely(toolbarLayout: CollapsingToolbarLayout): Boolean {
                    return textMatcher.matches(toolbarLayout.title)
                }
            }
        }
    }
}