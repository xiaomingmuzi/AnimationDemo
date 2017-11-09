package com.lixm.animationdemo.gson

import com.google.gson.annotations.SerializedName

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
class Basic {
    @SerializedName("city")
    var cityName: String? = null
    @SerializedName("id")
    var weatherId: String? = null

    var update: Update? = null

    inner class Update {
        @SerializedName("loc")
        var updateTime: String? = null
    }
}