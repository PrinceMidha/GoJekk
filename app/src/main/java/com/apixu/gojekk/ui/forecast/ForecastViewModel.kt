package com.apixu.gojekk.ui.forecast

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations.switchMap
import com.apixu.gojekk.data.repo.ForecastRepository
import com.apixu.gojekk.livedata.AbsentLiveData
import com.apixu.gojekk.model.Resource
import com.apixu.gojekk.model.forecast.Forecast
import com.apixu.gojekk.model.forecast.WeatherResponse
import com.apixu.gojekk.ui.common.BaseViewModel
import javax.inject.Inject

/**
 * Created by PRince Midha on 30/01/19.
 */
open class ForecastViewModel @Inject constructor(private val forecastRepository: ForecastRepository) :
    BaseViewModel() {

    private val fetchForecastTrigger = MutableLiveData<ForecastParams>()

    internal val forecastResponse =
        switchMap(fetchForecastTrigger) {
            if (it != null) {
                forecastRepository.getForecast()
            } else {
                AbsentLiveData.create<Resource<WeatherResponse>>()
            }
        }

    fun getWeatherForecast() {
        fetchForecastTrigger.value = ForecastParams("122001")
    }

    data class ForecastParams(
        val location: String?,
        val noOfDays: Int = 4
    )
}
