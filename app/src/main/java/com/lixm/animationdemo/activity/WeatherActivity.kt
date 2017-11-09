package com.lixm.animationdemo.activity

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import com.lixm.animationdemo.R

class WeatherActivity : Activity() {

    private var mWeatherId: String? = null

//    val drawerLayout by lazy {
//        findViewById(R.id.drawer_layout) as DrawerLayout
//    }
//
//    val swipeRefresh by lazy {
//        findViewById(R.id.swipe_refresh) as SwipeRefreshLayout
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT>=21){
            val decorView = getWindow().getDecorView()
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or  View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        }
        setContentView(R.layout.activity_weather)

        val prefs=PreferenceManager.getDefaultSharedPreferences(this)
        val weatherString=prefs.getString("weather",null)
        if (weatherString!=null){
//            val weather=
        }
    }
}

