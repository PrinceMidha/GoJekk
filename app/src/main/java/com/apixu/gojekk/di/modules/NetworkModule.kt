package com.apixu.gojekk.di.modules

import android.app.Application
import com.apixu.gojekk.data.api.RetrofitProvider
import com.apixu.gojekk.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Aditya Mehta on 16/04/18.
 */
@Module(includes = [ViewModelModule::class])
class NetworkModule {

    @Singleton
    @Provides
   internal  fun provideRetrofitService(application: Application): WeatherApi {
        return RetrofitProvider
                .provideDefaultRetrofit(application, true)
                .create(WeatherApi::class.java)
    }
}
