package com.apixu.gojekk.model.forecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Location : Serializable {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("region")
    @Expose
    var region: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("lat")
    @Expose
    var lat: Double? = null
    @SerializedName("lon")
    @Expose
    var lon: Double? = null
    @SerializedName("tz_id")
    @Expose
    var tzId: String? = null
    @SerializedName("localtime_epoch")
    @Expose
    var localtimeEpoch: Int? = null
    @SerializedName("localtime")
    @Expose
    var localtime: String? = null

}
