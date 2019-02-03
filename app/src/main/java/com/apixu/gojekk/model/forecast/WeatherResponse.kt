package com.apixu.gojekk.model.forecast


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeatherResponse : Serializable{

    @SerializedName("location")
    @Expose
    var location: Location? = null
    @SerializedName("current")
    @Expose
    var current: Current? = null
    @SerializedName("forecast")
    @Expose
    var forecast: Forecast? = null

}

