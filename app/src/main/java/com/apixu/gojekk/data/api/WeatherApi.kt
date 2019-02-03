package com.apixu.gojekk.data.api

import android.arch.lifecycle.LiveData
import com.apixu.gojekk.model.forecast.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Prince Midha dated 31/01/19.
 */
interface WeatherApi {

    @GET("forecast.json")
    fun getCurrentWeather(
        @Query("q") query: String,
        @Query("days") days: Int
    ): LiveData<ApiResponse<WeatherResponse>>
}
