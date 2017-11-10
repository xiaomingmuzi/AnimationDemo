package com.lixm.animationdemo.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.lixm.animationdemo.R
import com.lixm.animationdemo.gson.Weather
import com.lixm.animationdemo.service.AutoUpdateService
import com.lixm.animationdemo.utils.HttpUtil
import com.lixm.animationdemo.utils.Utility
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.weather_api.*
import kotlinx.android.synthetic.main.weather_forecast.*
import kotlinx.android.synthetic.main.weather_now.*
import kotlinx.android.synthetic.main.weather_suggestion.*
import kotlinx.android.synthetic.main.weather_title.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class WeatherActivity : BaseActivity() {

    private var mWeatherId: String? = null

    val drawerLayout by lazy {
        findViewById<DrawerLayout>(R.id.drawer_layout)
    }

    val swipeRefresh by lazy {
        findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = getWindow().getDecorView()
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        }
        setContentView(R.layout.activity_weather)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val weatherString = prefs.getString("weather", null)
        if (weatherString != null) {
            //via cache
            val weather = Utility.handleWeaherResponse(weatherString)
            weather?.let {
                mWeatherId = it.basic!!.weatherId
                showWeatherInfo(it)
            }
        } else {
            //via network
            mWeatherId = getIntent().getStringExtra("weather_id")
            mWeatherId?.let {
                weather_layout.visibility = View.INVISIBLE
                requestWeather(it)
            }
        }
        swipeRefresh.setOnRefreshListener {
            mWeatherId?.let {
                requestWeather(it)
            }
        }
        nav_button.setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }
        val bingPic=prefs.getString("bing_pic",null)
        if (bingPic!=null){
            Glide.with(this).load(bingPic).into(bing_pic_img)
        }else{
            loadBingPic()
        }
    }

    fun requestWeather(weatherId: String) {
        val weatherUrl = "${HttpUtil.Url}?cityid=$weatherId&${HttpUtil.Key}"
        HttpUtil.sendOkHttpRequest(weatherUrl, object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val responseText = response!!.body().string()
                val weather = Utility.handleWeaherResponse(responseText)
                runOnUiThread(Runnable {
                    if (weather != null && "ok" == weather.status){
                        val editor=PreferenceManager.getDefaultSharedPreferences(this@WeatherActivity).edit()
                        editor.putString("weather",responseText)
                        editor.apply()
                        mWeatherId=weather.basic!!.weatherId
                        showWeatherInfo(weather)
                    }else{
                        Toast.makeText(this@WeatherActivity,"request faild",Toast.LENGTH_SHORT).show()
                    }
                    swipeRefresh.isRefreshing=false
                })
            }

            override fun onFailure(p0: Call?, p1: IOException?) {
                p1!!.printStackTrace()
                runOnUiThread(Runnable {
                    Toast.makeText(this@WeatherActivity,"request faild",Toast.LENGTH_SHORT).show()
                    swipeRefresh.isRefreshing=false
                })
            }
        })
        loadBingPic()
    }

    private fun loadBingPic(){
        HttpUtil.sendOkHttpRequest(HttpUtil.Bing,object:Callback{
            override fun onFailure(p0: Call?, p1: IOException?) {
                p1!!.printStackTrace()
            }

            override fun onResponse(p0: Call?, p1: Response?) {
                val bingPic=p1!!.body().string()
                val editor=PreferenceManager.getDefaultSharedPreferences(this@WeatherActivity).edit()
                editor.putString("bing_pic",bingPic)
                editor.apply()
                runOnUiThread(Runnable {
                    Glide.with(this@WeatherActivity).load(bingPic).into(bing_pic_img)
                })
            }

        })
    }

    private fun showWeatherInfo(weather: Weather) {
        val cityName = weather.basic!!.cityName
        val updateTime = weather.basic!!.update!!.updateTime!!.split(" ".toRegex()).
                dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        val degree = weather.now!!.temperature!! + "℃"
        val weatherInfo = weather.now!!.more!!.info

        title_city.text = cityName
        title_update_time.text = updateTime
        degree_text.text = degree
        weather_info_text.text = weatherInfo

        forecast_layout.removeAllViews()
        weather.forecastList!!.map {
            val view = LayoutInflater.from(this).inflate(R.layout.weather_forecast_item,
                    forecast_layout, false)
            val dateText = view.findViewById<TextView>(R.id.date_text)
            val infoText = view.findViewById<TextView>(R.id.info_text)
            val maxText = view.findViewById<TextView>(R.id.max_text)
            val minText = view.findViewById<TextView>(R.id.min_text)

            dateText.text = it.date
            infoText.text = it.more!!.info
            maxText.text = it.temperature!!.max
            minText.text = it.temperature!!.min
            forecast_layout.addView(view)
        }

        weather.aqi?.let {
            aqi_text.text = it.city!!.aqi
            pm25_text.text = it.city!!.pm25
        }

        val comfort = "舒适度：" + weather.suggestion!!.comfort!!.info!!
        val carWash = "洗车指数：" + weather.suggestion!!.carWash!!.info!!
        val sport = "运动建议：" + weather.suggestion!!.sport!!.info!!

        comfort_text.text = comfort
        car_wash_text.text = carWash
        sport_text.text = sport
        weather_layout.visibility = View.VISIBLE

        val intent = Intent(this, AutoUpdateService::class.java)
        startService(intent)

    }

}

