package com.lixm.animationdemo.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.lixm.animationdemo.R
import com.lixm.animationdemo.activity.WeatherActivity
import com.lixm.animationdemo.activity.WeatherMainActivity
import com.lixm.animationdemo.db.City
import com.lixm.animationdemo.db.County
import com.lixm.animationdemo.db.Province
import com.lixm.animationdemo.utils.HttpUtil
import com.lixm.animationdemo.utils.Utility
import kotlinx.android.synthetic.main.weather_choose_area.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.litepal.crud.DataSupport
import org.xutils.common.util.LogUtil
import java.io.IOException

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
class ChooseAreaFragment : Fragment() {
    private var progressDialog: ProgressDialog? = null
    private val dataList = ArrayList<String>()
    private var provinceList: List<Province>? = null
    private var cityList: List<City>? = null
    private var countyList: List<County>? = null
    private var selectedProvince: Province? = null
    private var selectedCity: City? = null
    private var currentLevel: Int = 0

    /**
     * 半生对象
     */
    companion object {
        private val TAG = "ChooseAreaFragment"

        val LEVEL_PROVINCE = 0
        val LEVEL_CITY = 1
        val LEVEL_COUNTY = 2
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        LogUtil.w(TAG, "onCreateView")
        //断言一个表达式非空
        return inflater!!.inflate(R.layout.weather_choose_area, container, false)
    }

    private val adapter by lazy {
        ArrayAdapter(activity, android.R.layout.simple_list_item_1, dataList)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogUtil.w(TAG, "onCreateView")
        list_view.adapter = adapter
        list_view.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (currentLevel) {
                LEVEL_PROVINCE -> {
                    LogUtil.w(TAG, "onActivityCreated" + currentLevel)
                    selectedProvince = provinceList!![position]
                    queryCities()
                }
                LEVEL_CITY -> {
                    selectedCity = cityList!![position]
                    queryCounties()
                }
                LEVEL_COUNTY -> {
                    val weatherId = countyList!![position].weatherId
                    weatherId?.let {
                        when (activity) {
                            is WeatherMainActivity -> {
                                // :: 创建一个成员引用或者一个类引用
                                val intent = Intent(activity, WeatherActivity::class.java)
                                intent.putExtra("weather_id", weatherId)
                                startActivity(intent)
                                activity.finish()
                            }
                            is WeatherActivity -> {
                                val activity = activity as WeatherActivity
                                activity.drawerLayout.closeDrawers()
                                activity.swipeRefresh.isRefreshing = true
                                activity.requestWeather(weatherId)
                            }
                            else -> {
                            }
                        }
                    }
                }
                else -> {
                }
            }
        }
        back_button.setOnClickListener{
            when(currentLevel){
                LEVEL_COUNTY->{
                    queryCities()
                }
                LEVEL_CITY->{
                    queryProvinces()
                }
                else ->{}
            }
        }
        queryProvinces()
    }

    private fun queryProvinces() {
        title_text.text = "中国"
        back_button.visibility = View.GONE
        provinceList = DataSupport.findAll(Province::class.java)
        provinceList?.let {
            if (it.isNotEmpty()) {
                dataList.clear()
                it.map {
                    dataList.add(it.provinceName!!)
                }
                adapter.notifyDataSetChanged()
                list_view.setSelection(0)
                currentLevel = LEVEL_PROVINCE
                return
            }
        }
        queryFromServer(HttpUtil.China, "province")
    }

    private fun queryCities() {
        title_text.text = selectedProvince!!.provinceName
        back_button.visibility = View.VISIBLE

        cityList = DataSupport.where("provinceid = ?", selectedProvince!!.id.toString())
                .find(City::class.java)
        cityList?.let {
            if (it.isNotEmpty()) {
                dataList.clear()
                it.map {
                    dataList.add(it.cityName!!)
                }
                adapter.notifyDataSetChanged()
                list_view.setSelection(0)
                currentLevel = LEVEL_CITY
                return
            }
        }
        var provinceCode = selectedProvince!!.provinceCode
        val address = "${HttpUtil.China}/$provinceCode"
        queryFromServer(address, "city")

    }

    private fun queryCounties() {
        title_text.text = selectedCity!!.cityName
        back_button.visibility = View.VISIBLE
        countyList = DataSupport.where("cityid = ?", selectedCity!!.id.toString())
                .find(County::class.java)
        countyList?.let {
            if (it.isNotEmpty()) {
                dataList.clear()
                it.map { dataList.add(it.countyName!!) }
                adapter.notifyDataSetChanged()
                list_view.setSelection(0)
                currentLevel = LEVEL_COUNTY
                return
            }
        }
        val provinceCode = selectedProvince!!.provinceCode
        val cityCode = selectedCity!!.cityCode
        val address = HttpUtil.China + "/${provinceCode}/${cityCode}"
        queryFromServer(address, "county")
    }

    private fun queryFromServer(address: String, type: String) {
        LogUtil.w("address：$address \n  type：$type")
        showProgressDialog()
        HttpUtil.sendOkHttpRequest(address, object : Callback {
            override fun onFailure(p0: Call?, p1: IOException?) {
                activity.runOnUiThread(Runnable {
                    closeProgressDialog()
                    Toast.makeText(activity, "load faild", Toast.LENGTH_SHORT).show()
                })
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response?) {
                val responseText = response!!.body().string()
                LogUtil.i("queryFromServer："+responseText)
                var result = false
                when (type) {
                    "province" -> {
                        result = Utility.handleProvinceResponse(responseText)
                    }
                    "city" -> {
                        result = Utility.handleCityReponse(responseText, selectedProvince!!.id)
                    }
                    "county" -> {
                        result = Utility.handleCountyResponse(responseText, selectedCity!!.id)
                    }
                }
                if (result) {
                    activity.runOnUiThread(Runnable {
                        closeProgressDialog()
                        when (type) {
                            "province" -> {
                                queryProvinces()
                            }
                            "city" -> {
                                queryCities()
                            }
                            "county" -> {
                                queryCounties()
                            }
                        }
                    })
                }

            }

        })
    }

    private fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(activity, "", "loading...")
        }
        progressDialog?.show()
    }

    private fun closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog?.dismiss()
        }
    }
}
