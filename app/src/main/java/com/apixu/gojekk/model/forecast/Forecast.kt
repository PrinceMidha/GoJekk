package com.apixu.gojekk.model.forecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Forecast : Serializable {

    @SerializedName("forecastday")
    @Expose
    var forecastday: List<Forecastday>? = null

}
