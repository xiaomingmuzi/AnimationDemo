package com.lixm.animationdemo.gson

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
class AQI {
    // inner class will hold the reference of out class
    inner class AQICity {
        var aqi: String? = null
        var pm25: String? = null
    }

    var city: AQICity? = null

}