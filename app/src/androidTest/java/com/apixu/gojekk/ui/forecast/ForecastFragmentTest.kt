package com.apixu.gojekk.ui.forecast

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.apixu.gojekk.MainActivity
import com.apixu.gojekk.R
import com.apixu.gojekk.model.Resource
import com.apixu.gojekk.model.forecast.WeatherResponse
import com.apixu.gojekk.util.DummyResponse
import com.apixu.gojekk.util.EspressoTestUtil
import com.apixu.gojekk.util.TaskExecutorWithIdlingResourceRule
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.view.View
import org.hamcrest.CoreMatchers.*
import org.mockito.Mockito.mock
import android.view.ViewGroup
import com.apixu.gojekk.util.EspressoTestUtil.withColor
import com.apixu.gojekk.util.EspressoTestUtil.withFontSize
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.runner.Description


@RunWith(AndroidJUnit4::class)
class ForecastFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    private lateinit var viewModel: ForecastViewModel

    lateinit var forecastFragment: ForecastFragment
    private val apiResponse = MutableLiveData<Resource<WeatherResponse>>()

    @Before
    fun setUp() {
        forecastFragment = ForecastFragment()
        viewModel = mock(ForecastViewModel::class.java)
    }

    @Test
    fun testLoaded() {
        val repo = DummyResponse.successResponse()
        apiResponse.postValue(Resource.success(repo))
        onView(withId(R.id.currentTemp)).check(
            ViewAssertions.matches(
                withText("5.2" + 0x00B0.toChar() + "C")
            )
        )

        onView(withId(R.id.currentCity)).check(
            ViewAssertions.matches(
                withText("Newark")
            )
        )
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler))
            .check(matches(hasDescendant(withText("Tuesday"))))

        onView(withId(R.id.currentCity)).check(matches(withFontSize(activityRule.activity.resources.getDimension(R.dimen.city_name_size))))
        onView(withId(R.id.currentCity)).check(matches(withColor(activityRule.activity.resources.getColor(R.color.city_name_color))))

        onView(withId(R.id.currentTemp)).check(matches(withFontSize(activityRule.activity.resources.getDimension(R.dimen.current_temp_size))))
        onView(withId(R.id.currentTemp)).check(matches(withColor(activityRule.activity.resources.getColor(R.color.item_color))))

        onView(withId(R.id.recycler))
            .check(matches(hasDescendant(withFontSize(activityRule.activity.resources.getDimension(R.dimen.item_font_size)))))

        onView(withId(R.id.recycler))
            .check(matches(hasDescendant(withColor(activityRule.activity.resources.getColor(R.color.item_color)))))

    }

//    @Test
//    fun testError() {
//        apiResponse.postValue(Resource.error("foo", null))
//        onView(withId(R.id.progress_bar)).check(matches(CoreMatchers.not(isDisplayed())))
//        onView(withId(R.id.retry)).check(matches(isDisplayed()))
//        onView(withId(R.id.retry)).perform(click())
//        verify(viewModel).retry()
//        repoLiveData.postValue(Resource.loading(null))
//
//        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
//        onView(withId(R.id.retry)).check(matches(CoreMatchers.not(isDisplayed())))
//        val repo = TestUtil.createRepo("owner", "name", "desc")
//        repoLiveData.postValue(Resource.success(repo))
//
//        onView(withId(R.id.progress_bar)).check(matches(CoreMatchers.not(isDisplayed())))
//        onView(withId(R.id.retry)).check(matches(CoreMatchers.not(isDisplayed())))
//        onView(withId(R.id.name)).check(
//            matches(
//                withText(getString(R.string.repo_full_name, "owner", "name"))
//            )
//        )
//        onView(withId(R.id.description)).check(matches(withText("desc")))
//    }
}