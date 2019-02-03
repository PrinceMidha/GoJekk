package com.apixu.gojekk.ui.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import android.widget.TextView
import com.apixu.gojekk.Constants
import com.apixu.gojekk.MainActivity
import com.apixu.gojekk.R
import com.apixu.gojekk.model.Resource
import com.apixu.gojekk.model.forecast.WeatherResponse
import com.apixu.gojekk.ui.error.ErrorFragment
import com.apixu.gojekk.ui.forecast.ForecastFragment
import com.apixu.gojekk.ui.forecast.ForecastViewModel
import com.apixu.gojekk.util.DummyResponse
import com.apixu.gojekk.util.TaskExecutorWithIdlingResourceRule
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock

@MediumTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()
//    @Rule
//    @JvmField
//    val countingAppExecutors = CountingAppExecutorsRule()

    private lateinit var viewModel: ForecastViewModel
    private val apiResponse = MutableLiveData<Resource<WeatherResponse>>()

    @Before
    fun setUp() {
        viewModel = mock(ForecastViewModel::class.java)
//        viewModel.forecastResponse.observeForever {
//            Log.e("observed","testing")
//        }
//        Mockito.doNothing().`when`(viewModel).getWeatherForecast()
//        Mockito.`when`(viewModel.forecastResponse).thenReturn(apiResponse)
    }

    @Test
    fun testLoaded() {
        val repo = DummyResponse.successResponse()
        apiResponse.postValue(Resource.success(repo))
        onView(withId(R.id.loader)).check(matches(CoreMatchers.not(isDisplayed())))

        var forecast = ForecastFragment()
        var bundle = Bundle()
        bundle.putSerializable(Constants.FORECAST_BUNDLE,apiResponse?.value?.data)
        forecast.arguments = bundle
        activityRule.activity.replaceFragment(forecast)
        getInstrumentation().waitForIdleSync()

        var view = forecast.view?.findViewById<TextView>(R.id.currentTemp)
        assertNotNull(view)
    }

    @Test
    fun testError() {
        apiResponse.postValue(Resource.error(Throwable("Failed"), null))
        onView(withId(R.id.loader)).check(matches(CoreMatchers.not(isDisplayed())))
        var errorFragment = ErrorFragment()
        activityRule.activity.replaceFragment(errorFragment)
        onView(withId(R.id.retryButton)).check(matches(isDisplayed()))
        onView(withId(R.id.errorText)).check(
            matches(
                withText(activityRule.activity.getString(R.string.error_text))
            )
        )
    }
}