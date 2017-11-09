package com.lixm.animationdemo.gson

import com.google.gson.annotations.SerializedName

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
class Forecast {
    inner class More {
        @SerializedName("txt_d")
        var info: String? = null
    }

    inner class Temperature {
        var max: String? = null
        var min: String? = null
    }

    var date: String? = null
    @SerializedName("tmp")
    var temperature:Temperature?=null

    @SerializedName("cond")
    var more:More?=null


}
