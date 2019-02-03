package com.apixu.gojekk

import android.app.Application
import com.apixu.gojekk.data.api.WeatherApi
import com.apixu.gojekk.di.modules.NetworkModule
import org.mockito.Mockito

class TestModule: NetworkModule() {
     override fun provideRetrofitService(application: Application): WeatherApi {
        return Mockito.mock(WeatherApi::class.java);
    }
}