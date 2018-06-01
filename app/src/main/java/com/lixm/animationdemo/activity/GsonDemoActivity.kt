package com.lixm.animationdemo.activity

import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lixm.animationdemo.R
import com.lixm.animationdemo.gson.DynamicNewBean
import com.lixm.animationdemo.gson.DynamicTypeAdapter
import com.lixm.animationdemo.utils.HttpUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class GsonDemoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson_demo)
        val gsonBuilder: GsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(DynamicNewBean::class.java, DynamicTypeAdapter(4))
        gsonBuilder.setPrettyPrinting()

        val gson: Gson = Gson()
        val dynamicNewBean = DynamicNewBean()
        HttpUtil.sendOkHttpRequest(HttpUtil.Dynamic,object :Callback{
            override fun onFailure(p0: Call?, p1: IOException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(p0: Call?, p1: Response?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}
