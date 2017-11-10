package com.lixm.animationdemo.db

import org.litepal.crud.DataSupport

/**
 * @author Lixm
 * @date 2017/11/10
 * @detail
 */
class County(var id: Int = 0,
             var countyName: String? = null,
             var weatherId: String? = null,
             var cityId: Int = 0
) : DataSupport() {
}