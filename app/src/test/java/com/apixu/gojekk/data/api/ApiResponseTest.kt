package com.apixu.gojekk.data.api

import okhttp3.MediaType
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun exception() {
        val exception = Throwable("GoJekk")
        val (errorMessage) = ApiResponse.create<String>(exception)
        assertThat<String>(errorMessage.message, `is`("GoJekk"))
    }

    @Test
    fun success() {
        val apiResponse = ApiResponse.create<String>(Response.success("GoJekk"))
        if (apiResponse is ApiSuccessResponse) {
            assertThat<String>(apiResponse.body, `is`("GoJekk"))
        }
    }
}