package com.lixm.animationdemo.activity

import android.os.Bundle
import com.lixm.animationdemo.R

class GsonDemoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson_demo)
        val s = "{\n" +
                "                                                                                                            \t\"result\":10002,\n" +
                "                                                                                                            \t\"msg\":\"\",\n" +
                "                                                                                                            \t\"MerList\":[\n" +
                "                                                                                                            \t\t\n" +
                "                                                                                                            \t]\n" +
                "                                                                                                            }"
//        val g = Gson()
//        val result: Result<UserInfoBean>=   g.fromJson<Any>("", object : TypeToken<Result>() {
//
//        }.type)
//        println(result)
    }
}
