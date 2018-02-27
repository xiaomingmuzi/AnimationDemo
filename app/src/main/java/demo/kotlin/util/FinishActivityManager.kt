package com.lixm.kotlin.util

import android.app.Activity
import com.lixm.kotlin.activity.BaseActivity
import java.lang.ref.WeakReference
import java.util.*

/**
 * @author Lixm
 * @date 2017/11/9
 * @detail
 */
object FinishActivityManager : BaseActivity() {

    private var mActivityStack = Stack<WeakReference<Activity>>()

    fun addActivity(activity: Activity) {
        mActivityStack.add(WeakReference(activity))
    }

    fun checkWeakReference() {
        for (it in mActivityStack) {
            var temp = it.get()
            if (temp == null)
                mActivityStack.remove(it)
        }
    }

    fun currentActivity(): Activity? {
        checkWeakReference()
        if (!mActivityStack.isEmpty()) {
            return mActivityStack[mActivityStack.size - 1].get()
        }
        return null
    }

    fun finishActivity(activity: Activity) {
        for (it in mActivityStack) {
            var temp = it.get()
            if (temp == null) {
                mActivityStack.remove(it)
                continue
            }
            if (temp == activity)
                mActivityStack.remove(it)
        }
        activity.finish()
    }

    fun finishActivity() {
        var activity = currentActivity()
        if (activity != null) {
            finishActivity(activity)
        }
    }

    fun finishAllActivity() {
        for (it in mActivityStack) {
            var activity = it.get()
            if (activity != null)
                activity.finish()
        }
        mActivityStack.clear()
    }

    fun exitApp() {
        try {
            finishAllActivity()
            System.exit(0)
            android.os.Process.killProcess(android.os.Process.myPid())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}