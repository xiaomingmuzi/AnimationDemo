package com.lixm.animationdemo.db

import org.litepal.crud.DataSupport

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
data class City(var id:Int=0,
                var cityName:String?=null,
                var cityCode:Int=0,
                var provinceId:Int=0
                ):DataSupport()