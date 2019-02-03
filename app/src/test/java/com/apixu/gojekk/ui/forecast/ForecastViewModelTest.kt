package com.apixu.gojekk.ui.forecast

import android.arch.lifecycle.Observer
import com.apixu.gojekk.data.repo.ForecastRepository
import com.apixu.gojekk.model.Resource
import com.apixu.gojekk.model.forecast.WeatherResponse
import com.apixu.gojekk.util.mock
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class ForecastViewModelTest {

    private val repository = Mockito.mock(ForecastRepository::class.java)
    private var repoViewModel = ForecastViewModel(repository)

    @Test
    fun testNull() {
        MatcherAssert.assertThat(repoViewModel.forecastResponse, CoreMatchers.notNullValue())
        Mockito.verify(repository, Mockito.never()).getForecast()
    }

    @Test
    fun dontFetchWithoutObservers() {
        repoViewModel.getWeatherForecast()
        Mockito.verify(repository, Mockito.never()).getForecast()
    }

    @Test
    fun fetchWhenObserved() {
        repoViewModel.getWeatherForecast()
        repoViewModel.forecastResponse.observeForever(mock())
        Mockito.verify(repository).getForecast()
    }

    @Test
    fun changeWhileObserved() {
        repoViewModel.forecastResponse.observeForever(mock())

//        repoViewModel.setId("a", "b")
//        repoViewModel.setId("c", "d")

        //TODO
        //Need to change the im plementation to change the value for get weather forecast
        repoViewModel.getWeatherForecast()
        repoViewModel.getWeatherForecast()

//        Mockito.verify(repository).getForecast("a", "b")
//        Mockito.verify(repository).getForecast("c", "d")
    }

    //TODO
//    @Test
//    fun retry() {
//        repoViewModel.retry()
//        Mockito.verifyNoMoreInteractions(repository)
//        repoViewModel.setId("foo", "bar")
//        Mockito.verifyNoMoreInteractions(repository)
//        val observer = mock<Observer<Resource<WeatherResponse>>>()
//        repoViewModel.forecastResponse.observeForever(observer)
//        Mockito.verify(repository).getForecast("foo", "bar")
//        Mockito.reset(repository)
//        repoViewModel.retry()
//        Mockito.verify(repository).getForecast("foo", "bar")
//    }
//TODO
//    @Test
//    fun blankRepoId() {
//        repoViewModel.setId("", "")
//        val observer1 = mock<Observer<Resource<WeatherResponse>>>()
//        repoViewModel.repo.observeForever(observer1)
//        Mockito.verify(observer1).onChanged(null)
//    }
}