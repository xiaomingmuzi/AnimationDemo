package com.lixm.animationdemo.activity

import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lixm.animationdemo.R
import com.lixm.animationdemo.bean.HomeTabBean
import com.lixm.animationdemo.gson.DynamicNewBean
import com.lixm.animationdemo.gson.DynamicTypeAdapter
import com.lixm.animationdemo.gson.HomeTabTypeAdapter
import com.lixm.animationdemo.utils.HttpUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.xutils.common.util.LogUtil
import java.io.IOException

class GsonDemoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson_demo)
        homeTab()

//        dynamicBean()

    }

    private fun dynamicBean() {
        val gsonBuilder: GsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(DynamicNewBean::class.java, DynamicTypeAdapter(3))
        gsonBuilder.setPrettyPrinting()
        val gson: Gson = gsonBuilder.create()

        val gson2: Gson = Gson()

        HttpUtil.sendOkHttpRequest(HttpUtil.Dynamic, object : Callback {
            override fun onFailure(p0: Call?, p1: IOException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call?, response: Response?) {
                println("TypeAdapter    开始解析")
                var responseText = response!!.body().string()
                println(responseText)
                responseText = responseText.replace("\"LivingProductID\":null,", "")
                responseText = responseText.replace("\"TopicID\":0,", "")
                println("替换后："+responseText)
                try {
                val homeTabBean: DynamicNewBean = gson.fromJson(responseText, DynamicNewBean::class.java)
                println(homeTabBean.toString())

                LogUtil.e("======================")
                println("Gson    开始解析")
                println(responseText)
                val homeTabBean2: DynamicNewBean = gson2.fromJson(responseText, DynamicNewBean::class.java)
                println(homeTabBean2.toString())
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }

        })
    }

    private fun homeTab() {
        val gsonBuilder: GsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(DynamicNewBean::class.java, HomeTabTypeAdapter())
        gsonBuilder.setPrettyPrinting()
        val gson: Gson = gsonBuilder.create()

        val gson2: Gson = Gson()

        HttpUtil.sendOkHttpRequest(HttpUtil.HomeTab, object : Callback {
            override fun onFailure(p0: Call?, p1: IOException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call?, response: Response?) {
                println("TypeAdapter    开始解析")
                val responseText = response!!.body().string()
                println(responseText)
                val homeTabBean: HomeTabBean = gson.fromJson(responseText, HomeTabBean::class.java)
                println(homeTabBean.toString())

                LogUtil.e("======================")
                println("Gson    开始解析")
                println(responseText)
                val homeTabBean2: HomeTabBean = gson2.fromJson(responseText, HomeTabBean::class.java)
                println(homeTabBean2.toString())
            }

        })
    }
}
