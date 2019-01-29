package com.apixu.gojekk.data.api

import android.app.Application
import com.apixu.gojekk.data.api.interceptor.GenericInterceptor
import com.apixu.gojekk.livedata.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by PRince Midha on 29/01/19.
 * Utility class to provide Retrofit and OkHttp resources.
 */

object RetrofitProvider {

    fun provideDefaultRetrofit(context: Application, showNetworkLogs: Boolean): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://api.apixu.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient(context, showNetworkLogs))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    private fun provideOkHttpClient(context: Application, showNetworkLogs: Boolean): OkHttpClient {
        val cacheSize = 20 * 1024 * 1024L // 20 MB
        val cache = okhttp3.Cache(context.cacheDir, cacheSize)

        val builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        builder.addInterceptor(GenericInterceptor())

        // add all interceptors before [HttpLoggingInterceptor]
        // it should be the last one in order to log requests properly
        if (showNetworkLogs) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        builder.cache(cache)
        return builder.build()
    }
}
