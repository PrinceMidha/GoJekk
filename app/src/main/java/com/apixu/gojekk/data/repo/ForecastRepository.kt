package com.apixu.gojekk.data.repo

import android.arch.lifecycle.LiveData
import com.apixu.gojekk.data.api.ApiResponse
import com.apixu.gojekk.data.api.AppExecutors
import com.apixu.gojekk.data.api.WeatherApi
import com.apixu.gojekk.livedata.NetworkOnlyResource
import com.apixu.gojekk.model.Resource
import com.apixu.gojekk.model.forecast.Forecast
import com.apixu.gojekk.model.forecast.WeatherResponse
import javax.inject.Inject

/**
 *
 * Created by Prince Midha dated 31/01/19.
 */
open class ForecastRepository @Inject
internal constructor(
    private val appExecutors: AppExecutors,
    private val weatherApi: WeatherApi
) {

    fun getForecast(): LiveData<Resource<WeatherResponse>> {
        return object : NetworkOnlyResource<WeatherResponse>(appExecutors) {

            override fun createCall(): LiveData<ApiResponse<WeatherResponse>> {
                return weatherApi.getCurrentWeather("07112", 5)
            }
        }.asLiveData()
    }
}
