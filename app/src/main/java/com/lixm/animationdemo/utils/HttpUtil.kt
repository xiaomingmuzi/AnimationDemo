package com.lixm.animationdemo.utils

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * @author Lixm
 * @date 2017/11/10
 * @detail
 */
object HttpUtil {
    val Url = "http://guolin.tech/api/weather"
    val Key = "key=bc0418b57b2d4918819d3974ac1285d9"
    val Bing = "http://guolin.tech/api/bing_pic"
    val China = "http://guolin.tech/api/china"

    fun sendOkHttpRequest(address: String, callback: Callback) {
        val client = OkHttpClient()
        val request = Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)
    }
}