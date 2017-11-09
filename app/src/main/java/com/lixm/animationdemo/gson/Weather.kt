package com.lixm.animationdemo.gson

import com.google.gson.annotations.SerializedName

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
class Weather {
    var status: String? = null
    var basic: Basic? = null
    var aqi: AQI? = null
    var now: Now? = null
    var suggestion: Suggestion? = null

    @SerializedName("daily_forecast")
    var forecastList: List<Forecast>? = null
}