package com.lixm.animationdemo.utils

import android.text.TextUtils
import com.lixm.animationdemo.db.City
import com.lixm.animationdemo.db.Province
import org.json.JSONArray
import org.json.JSONException

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
object Utility {
    fun handleProvinceResponse(response: String): Boolean {
        if (!TextUtils.isEmpty(response)) {
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
                    city.save()
                }
                return true
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return false
    }

    fun handleCountyResponse(response: String,cityId:Int): Boolean? {
//        if (!TextUtils.isEmpty(response)){
//            try{
//               val allCounties=JSONArray(response)
////                for (i in allCounties.length()-1){}
//            }catch (e:Exception){
//                e.printStackTrace()
//            }
//        }
        return null
}}