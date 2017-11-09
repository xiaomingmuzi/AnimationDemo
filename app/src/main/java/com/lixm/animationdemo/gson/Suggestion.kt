package com.lixm.animationdemo.gson

import com.google.gson.annotations.SerializedName

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
class Suggestion {

    inner class Comfort {
        @SerializedName("txt")
        var info: String? = null
    }

    inner class CarWash {
        @SerializedName("txt")
        var info: String? = null
    }

    inner class Sport {
        @SerializedName("txt")
        var info: String? = null
    }

    @SerializedName("comf")
    var comfort: Comfort? = null

    @SerializedName("cw")
    var carWash: CarWash? = null

    var sport: Sport? = null
}