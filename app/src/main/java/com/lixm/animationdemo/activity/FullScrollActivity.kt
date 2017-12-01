package com.lixm.animationdemo.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.RelativeLayout
import com.lixm.animationdemo.R
import com.lixm.animationdemo.adapter.ListViewAdapter
import com.lixm.animationdemo.bean.TestData
import com.lixm.animationdemo.customview.CustomHScrollView
import org.xutils.common.util.LogUtil
import java.util.*

/**
 * @author Lixm
 * @date 2017/12/1
 * @detail
 */

class FullScrollActivity : BaseActivity() {

    private var mHead: RelativeLayout? = null
    private var mListView: ListView? = null
    private var mDataList: MutableList<TestData>? = null
    private var mAdapter: ListViewAdapter? = null
    private var leftPos: Int = 0//用于记录CustomHScrollView初始位置
    private var topPos: Int = 0
    private var mScrollView: CustomHScrollView? = null

    private var x: Float = 0.toFloat()
    private var y: Float = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_scroll)
        LogUtil.w("FullScrollLayoutActivity")
        initView()
        initData()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    private fun initView() {
        mListView = findViewById(R.id.list_view)
        mScrollView = findViewById(R.id.h_scrollview)
        mHead = findViewById(R.id.head_layout)
        mHead!!.setBackgroundColor(resources.getColor(R.color.red_record_title_tab))
        mHead!!.isFocusable = true
        mHead!!.isClickable = true
        mHead!!.setOnTouchListener(MyTouchLinstener())
        mListView!!.setOnTouchListener(MyTouchLinstener())
    }

    private fun initData() {
        mDataList = ArrayList()
        var data: TestData? = null
        for (i in 1..49) {
            data = TestData()
            data.text1 = "第" + i + "行-1"
            data.text2 = "第" + i + "行-2"
            data.text3 = "第" + i + "行-3"
            data.text4 = "第" + i + "行-4"
            data.text5 = "第" + i + "行-5"
            data.text6 = "第" + i + "行-6"
            data.text7 = "第" + i + "行-7"
            mDataList!!.add(data)
        }
        mAdapter = ListViewAdapter(this, mDataList, mHead)
        mListView!!.adapter = mAdapter
    }

    internal inner class MyTouchLinstener : View.OnTouchListener {

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            //当在表头和listview控件上touch时，将事件分发给ScrollView
            LogUtil.w("===========" + (v.id == R.id.list_view))
            val headScrollView: HorizontalScrollView = mHead!!.findViewById(R.id.h_scrollview)
            if (v.id == R.id.head_layout) {
                headScrollView.onTouchEvent(event)
                return false
            }
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    x = event.x
                    y = event.y
                    headScrollView.onTouchEvent(event)
                }
                MotionEvent.ACTION_MOVE -> {
                    val xDis = Math.abs(x - event.x)
                    val yDis = Math.abs(y - event.y)
                    LogUtil.w(xDis.toString() + "======" + yDis)
                    if (xDis > yDis && yDis < 50) {
                        headScrollView.onTouchEvent(event)
                        LogUtil.w("======横向滑动========")
                        return true
                    }
                }
                MotionEvent.ACTION_UP -> headScrollView.onTouchEvent(event)
            }
            return false
        }
    }

    fun setPosData(l: Int, t: Int) {
        this.leftPos = l
        this.topPos = t
    }
}
