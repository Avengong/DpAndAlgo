package com.tencent.avengong.designpattern.principles.ocp

import android.util.Log

class AlertNotification {

    fun nitify(tps: String) {

        Log.d("Alert", "$tps 通知了，目前只支持一种")
    }
}