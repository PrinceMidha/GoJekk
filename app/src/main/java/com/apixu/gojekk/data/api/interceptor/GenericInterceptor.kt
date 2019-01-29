package com.apixu.gojekk.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by PRince Midha on 29/01/19.
 */
internal class GenericInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(
                "key",
                "495e7a485f854cdfa7464800192801"
            )
            .build()
        return chain.proceed(request)!!
    }
}
