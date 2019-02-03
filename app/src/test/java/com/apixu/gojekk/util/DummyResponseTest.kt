package com.apixu.gojekk.util

import com.apixu.gojekk.model.forecast.Day
import com.apixu.gojekk.model.forecast.Forecast
import com.apixu.gojekk.model.forecast.Forecastday
import com.apixu.gojekk.model.forecast.WeatherResponse

object DummyResponseTest {

    fun successResponse() : WeatherResponse {
        var response = WeatherResponse()
        response.current = null
        response.location?.name = "Newark"
        response.forecast = Forecast()
        response.forecast!!.forecastday = arrayListOf(Forecastday())
        response.forecast!!.forecastday!![0].date = "2019-02-04"
        response.forecast!!.forecastday!![0].day = Day()
        response.forecast!!.forecastday!![0].day!!.maxtempC = 8.2
        return response
    }
}