package com.dadoufi.foursquare_client.search

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dadoufi.foursquare_client.DataBindingIdlingResource
import com.dadoufi.foursquare_client.MainActivity
import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.di.BaseUrlModule
import com.dadoufi.foursquare_client.lazyActivityScenarioRule
import com.dadoufi.foursquare_client.mockwebserver.SuccessDispatcher
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.FlowPreview
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test

@FlowPreview
@HiltAndroidTest
@UninstallModules(BaseUrlModule::class)
class SearchVenuesTest : TestCase() {

    @get:Rule
    val activityScenarioRule = lazyActivityScenarioRule<MainActivity>(launchActivity = false)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private var mockWebServer = MockWebServer()
    private lateinit var idlingResource: IdlingResource

    @Test
    fun test() = before {
        mockWebServer.start(8080)
        Intents.init()
    }.after {
        mockWebServer.shutdown()
        Intents.release()
        IdlingRegistry.getInstance().unregister(idlingResource)
    }.init {
        mockWebServer.dispatcher = SuccessDispatcher()
        activityScenarioRule.launch()

        idlingResource = DataBindingIdlingResource(activityScenarioRule)
        IdlingRegistry.getInstance().register(idlingResource)
    }.run {
        step("Type search query") {
            onView(
                withId(R.id.searchView)
            ).perform(SearchViewActionExtension.typeText("pizza"))
                .perform(SearchViewActionExtension.submitText("pizza"))
        }
        step("Check first item") {
            SearchScreen {
                recycler.firstChild<SearchScreen.MainItem> {
                    isVisible()
                    title { hasText("New York Pizza") }
                    subtitle {
                        hasText(
                            "Damstraat 24\n" +
                                    "1012 JM Amsterdam\n" +
                                    "Nederland"
                        )
                    }
                }
            }
        }
        step("Scroll to bottom") {
            SearchScreen {
                recycler.scrollToEnd()
            }
        }
    }

}
