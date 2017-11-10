package com.lixm.animationdemo.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * @author Lixm
 * @date 2017/11/10
 * @detail
 */
class AutoUpdateService:Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}