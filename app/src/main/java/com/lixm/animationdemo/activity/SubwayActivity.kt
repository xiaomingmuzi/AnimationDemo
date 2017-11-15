package com.lixm.animationdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lixm.animationdemo.R
import org.xutils.common.util.LogUtil

class SubwayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subway)
        LogUtil.w("单程4元时，22天工作日总额为：" + total(4.0))
        LogUtil.w("单程6元时，22天工作日总额为：" + total(6.0))
    }

    fun total(price: Double): Double {
        var total = 0.0
        var sum = 0.0
        while (total < 100) {//100以内，不打折
            total += price
            sum++
            if (sum % 2 == 0.0)
                LogUtil.i("第${Math.ceil(sum / 2)}天，花了${total}元")
        }
        LogUtil.w("第${Math.ceil(sum / 2)}天，满100元")
        if (sum % 2 != 0.0) {//满整百时，是早起刷卡，但需要晚上回家才能换卡
            total += price * 0.8
            sum++
            LogUtil.w("第${Math.ceil(sum / 2)}天，花了${total}元")
        }
        while (total < 150) {//150以内，打8折
            total += price * 0.8
            sum++
            if (sum % 2 == 0.0)
                LogUtil.i("第${sum / 2}天，花了${total}元")
        }
        LogUtil.w("第${sum / 2}天，满150元")
        if (sum % 2 != 0.0) {
            total += price * 0.5
            sum++
            LogUtil.w("第${Math.ceil(sum / 2)}天，花了${total}元")
        }
        while (sum / 2 <= 22) {
            total += price * 0.5
            sum++
            if (sum % 2 == 0.0)
                LogUtil.i("第${sum / 2}天，花了${total}元")
        }
        return total
    }
}
