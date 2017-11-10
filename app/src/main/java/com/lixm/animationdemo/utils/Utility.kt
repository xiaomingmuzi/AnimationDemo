package com.lixm.animationdemo.utils

import android.text.TextUtils
import com.google.gson.Gson
import com.lixm.animationdemo.db.City
import com.lixm.animationdemo.db.County
import com.lixm.animationdemo.db.Province
import com.lixm.animationdemo.gson.Weather
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.xutils.common.util.LogUtil

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
object Utility {
    fun handleProvinceResponse(response: String): Boolean {
        if (!TextUtils.isEmpty(response)) {
            LogUtil.w("handleProvinceResponse",response)
            try {
                val allProvinces = JSONArray(response)
                for (i in 0..allProvinces.length() - 1) {
                    val provinceObject = allProvinces.getJSONObject(i)
                    val province = Province()
                    province.provinceName = provinceObject.optString("name")
                    province.provinceCode = provinceObject.optInt("id")
                    province.save()
                }
                return true
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return false
    }

    fun handleCityReponse(response: String, provinceId: Int): Boolean {
        if (!TextUtils.isEmpty(response)) {
            try {
                val allCities = JSONArray(response)
                for (i in 0..allCities.length() - 1) {
                    val cityObject = allCities.getJSONObject(i)
                    val city = City()
                    city.cityName = cityObject.optString("name")
                    city.cityCode = cityObject.optInt("id")
                    city.provinceId=provinceId
                    city.save()
                }
                return true
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return false
    }

    fun handleCountyResponse(response: String, cityId: Int): Boolean {
        if (!TextUtils.isEmpty(response)) {
            try {
                val allCounties = JSONArray(response)
                for (i in 0..allCounties.length() - 1) {
                    val countyObject = allCounties.getJSONObject(i)
                    val county = County()
                    county.countyName = countyObject.optString("name")
                    county.weatherId = countyObject.optString("weather_id")
                    county.cityId = cityId
                    county.save()
                }
                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }

    fun handleWeaherResponse(response: String): Weather? {
        LogUtil.i("handleWeaherResponse：$response")
        try {
            val jsonObject = JSONObject(response)
            val jsonArray = jsonObject.optJSONArray("HeWeather")
            val weatherContent = jsonArray.optJSONObject(0).toString()

            return Gson().fromJson(weatherContent, Weather::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}