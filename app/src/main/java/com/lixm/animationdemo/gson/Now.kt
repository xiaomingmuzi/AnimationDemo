package com.lixm.animationdemo.gson

import com.google.gson.annotations.SerializedName

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
class Now {
    inner class More {
        @SerializedName("txt")
        var info: String? = null
    }

    @SerializedName("tmp")
    var temperature: String? = null

    @SerializedName("cond")
    var more: More? = null
}