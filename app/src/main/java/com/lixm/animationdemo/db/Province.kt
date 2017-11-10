package com.lixm.animationdemo.db

import org.litepal.crud.DataSupport

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
data class Province(var id: Int = 0,
                    var provinceName: String? = null,
                    var provinceCode: Int = 0
) : DataSupport(){}