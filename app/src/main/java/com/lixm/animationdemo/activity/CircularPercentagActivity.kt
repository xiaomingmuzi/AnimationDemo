package com.lixm.animationdemo.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.lixm.animationdemo.R
import com.lixm.animationdemo.customview.CircularPercentagView
import com.lixm.animationdemo.customview.WaterRippleView

class CircularPercentagActivity : BaseActivity() {

    val btn_reset by lazy {
        findViewById<Button>(R.id.btn_reset)
    }

    val circularPercentagView by lazy {
        findViewById<CircularPercentagView>(R.id.circularPercentagView)
    }

    val water_ripple_view by lazy {
        findViewById<WaterRippleView>(R.id.water_ripple_view)
    }

    val btn_start by lazy {
        findViewById<Button>(R.id.btn_start)
    }

    val btn_stop by lazy {
        findViewById<Button>(R.id.btn_stop)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_percentage)
        btn_reset.setOnClickListener(View.OnClickListener { circularPercentagView.endAngle = 270 })
        btn_start.setOnClickListener(View.OnClickListener {
            water_ripple_view.start()
        })
        btn_stop.setOnClickListener(View.OnClickListener {
            water_ripple_view.stop()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        water_ripple_view.stop()
    }
}
