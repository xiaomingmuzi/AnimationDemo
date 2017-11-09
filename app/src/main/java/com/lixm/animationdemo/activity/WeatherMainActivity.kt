package com.lixm.animationdemo.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import com.lixm.animationdemo.R

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
class WeatherMainActivity :Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        val prefs=PreferenceManager.getDefaultSharedPreferences(this@WeatherMainActivity)
        var weather=prefs.getString("weather",null)
        weather?.let {
            var intent=Intent(this,WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}