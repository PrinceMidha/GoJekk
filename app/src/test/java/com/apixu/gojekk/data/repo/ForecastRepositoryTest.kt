package com.apixu.gojekk.data.repo

import com.apixu.gojekk.data.api.WeatherApi
import com.apixu.gojekk.util.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class ForecastRepositoryTest {

//    @Rule
//    @JvmField
//    val instantExecutorRule = InstantTa()

    private val retrofitService = Mockito.mock(WeatherApi::class.java)
    private val repo = ForecastRepository(InstantAppExecutors(), retrofitService)

    @Test
    fun getForecast() {
        var weatherResponse = DummyResponseTest.successResponse()
        val call = ApiUtil.successCall(weatherResponse)
        `when`(retrofitService!!.getCurrentWeather("07112",5)).thenReturn(call)
//        val observer = mock<Observer<Resource<WeatherResponse>>>()
//        repo.getForecast().observeForever(observer)
//        Mockito.verify(retrofitService, Mockito.never()).getCurrentWeather("07112",5)
//        Mockito.verify(retrofitService).getCurrentWeather("07112",5)
    }
}