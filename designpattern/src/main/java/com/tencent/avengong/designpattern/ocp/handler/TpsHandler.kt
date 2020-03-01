package com.tencent.avengong.designpattern.ocp.handler

import android.util.Log
import com.tencent.avengong.designpattern.ocp.AlertBean
import com.tencent.avengong.designpattern.ocp.AlertNotification
import com.tencent.avengong.designpattern.ocp.AlertRule

class TpsHandler(mAlertRule: AlertRule, mAlertNotification: AlertNotification) :
    AlertHnadler(mAlertRule, mAlertNotification) {
    override fun check(alertBean: AlertBean) {

        val tps = alertBean.requestCount / alertBean.durationOfSeconds
        if (tps > mAlertRule.getMatchRules(alertBean.api)!!.mMaxTps) {
            Log.d("Alert--", "tps warn  out !!!!")
            mAlertNotification.nitify("tps")
        }
    }
}