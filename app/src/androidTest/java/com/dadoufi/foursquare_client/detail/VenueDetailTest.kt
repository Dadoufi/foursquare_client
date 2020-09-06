package com.dadoufi.foursquare_client.detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.detail.CollapsibleToolbarTitleViewActionExtension.Companion.withCollapsibleToolbarTitle
import com.dadoufi.foursquare_client.di.BaseUrlModule
import com.dadoufi.foursquare_client.launchFragmentInHiltContainer
import com.dadoufi.foursquare_client.mockwebserver.SuccessDispatcher
import com.dadoufi.foursquare_client.ui.detail.DetailFragment
import com.dadoufi.foursquare_client.ui.detail.DetailFragmentArgs
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.core.Is.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@FlowPreview
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@UninstallModules(BaseUrlModule::class)
@HiltAndroidTest
class VenueDetailTest : TestCase() {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private var mockWebServer = MockWebServer()

    @Test
    fun test() = before {
        mockWebServer.start(8080)
        Intents.init()
    }.after {
        mockWebServer.shutdown()
        Intents.release()
    }.init {
        mockWebServer.dispatcher = SuccessDispatcher()
        val bundle = DetailFragmentArgs("4a27db7bf964a52016941fe3").toBundle()
        launchFragmentInHiltContainer<DetailFragment>(bundle, R.style.AppTheme)

    }.run {
        step("Check description") {
            DetailScreen {
                description { hasText("New York Pizza is al sinds de oprichting in 1993 dé smaakmaker van Nederland") }
            }
        }
        step("Check rating") {
            DetailScreen {
                rating { hasText("6.6") }
            }
        }
        step("Check address") {
            DetailScreen {
                address {
                    hasText(
                        "Damstraat 24\n" +
                                "1012 JM Amsterdam\n" +
                                "Nederland"
                    )
                }
            }
        }
        step("Check phone") {
            DetailScreen {
                phone {
                    hasText("+31 20 422 2123")
                    isClickable()
                }
            }
        }
        step("Check twitter") {
            DetailScreen {
                twitter {
                    hasText("new_york_pizza")
                    isClickable()
                }
            }
        }
        step("Check instagram") {
            DetailScreen {
                instagram {
                    hasText("newyorkpizza_nl")
                    isClickable()
                }
            }
        }
        step("Check facebook") {
            DetailScreen {
                facebook {
                    isGone()
                }
            }
        }
        step("Check Title") {
            onView(isAssignableFrom(CollapsingToolbarLayout::class.java)).check(
                matches(
                    withCollapsibleToolbarTitle(`is`("New York Pizza"))
                )
            )
        }
    }


}