package com.apixu.gojekk.data.api

import android.support.v4.util.ArrayMap
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.util.regex.Pattern

/**
 * Common class used by REMOTE responses.
 *
 * @param <T>
</T> */
class ApiResponse<T> {
    private val code: Int
    val body: T?
    //    val errorMessage: String?
    val error: Throwable?
    private val responseUrl: String?
    private val links: MutableMap<String, String>

    val isSuccessful: Boolean
        get() = code in 200..299

    constructor(error: Throwable) {
        code = -1
        body = null
        responseUrl = null

        this.error = error

        links = mutableMapOf()
    }

    constructor(response: Response<T>) {
        code = response.code()
        body = response.body()
        responseUrl = response.raw().request().url().toString()
        if (isSuccessful) {
            error = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody()!!.string()
                } catch (ignored: IOException) {
                    Timber.e(ignored, "error while parsing response")
                }
            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            error = Throwable(message)
        }
        val linkHeader = response.headers().get("link")
        if (linkHeader == null) {
            links = mutableMapOf()
        } else {
            links = ArrayMap()
            val matcher = LINK_PATTERN.matcher(linkHeader)

            while (matcher.find()) {
                val count = matcher.groupCount()
                if (count == 2) {
                    links[matcher.group(2)] = matcher.group(1)
                }
            }
        }
    }

    companion object {
        private val LINK_PATTERN = Pattern
            .compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
    }
}
