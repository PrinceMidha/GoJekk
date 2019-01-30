package com.apixu.gojekk.data.api

import android.arch.lifecycle.LiveData
import com.apixu.gojekk.model.forecast.Forecast
import retrofit2.http.GET

/**
 * @author Vipul Kumar; dated 28/01/19.
 */
interface WeatherApi {

    @GET("forecast.json")
    fun getCurrentWeather(): LiveData<ApiResponse<Forecast>>
}
