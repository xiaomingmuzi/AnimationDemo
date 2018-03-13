package com.lixm.animationdemo.customview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView

/**
 * @author Lixm
 * @date 2018/3/13
 * @detail 处理EditText的滑动事件和ScrollView双重滑动冲突
 */
class MyLinearLayoutKT : LinearLayout {

    var TAG = javaClass.canonicalName
    var parentScrollView: ScrollView? = null
    var editText: EditText? = null
    var showLineMax = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    /**
     * 设置父控件ScrollView
     */
    fun setParentScrollview(parentScrollview: ScrollView) {
        parentScrollView = parentScrollview
    }

    /**
     * 设置EditText
     */
    fun setChildEditText(editText: EditText) {
        this.editText = editText
        val lp = editText.layoutParams
        showLineMax = lp.height / editText.lineHeight
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (parentScrollView == null)
            return super.onInterceptTouchEvent(ev)
        else {
            if (ev!!.action == MotionEvent.ACTION_DOWN && editText?.lineCount!! >= showLineMax) {
                setParentScrollAble(false)
            }else if(ev!!.action==MotionEvent.ACTION_UP){
                setParentScrollAble(true)
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    /**
     * 传递父控件的点击事件
     */
    fun setParentScrollAble(flag : Boolean){
        parentScrollView!!.requestDisallowInterceptTouchEvent(!flag)
    }
}