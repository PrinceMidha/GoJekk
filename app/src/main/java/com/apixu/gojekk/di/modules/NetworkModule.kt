package com.apixu.gojekk.di.modules

import android.app.Application
import com.apixu.gojekk.data.api.RetrofitProvider
import com.apixu.gojekk.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Prince Midha.
 */
@Module(includes = [ViewModelModule::class])
open class NetworkModule {

    @Singleton
    @Provides
   internal open fun provideRetrofitService(application: Application): WeatherApi {
        return RetrofitProvider
                .provideDefaultRetrofit(application, true)
                .create(WeatherApi::class.java)
    }
}
