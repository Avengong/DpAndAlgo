package com.tencent.avengong.designpattern.ocp.handler

import android.util.Log
import com.tencent.avengong.designpattern.ocp.AlertBean
import com.tencent.avengong.designpattern.ocp.AlertNotification
import com.tencent.avengong.designpattern.ocp.AlertRule

class TimeOuntHandler(mAlertRule: AlertRule, mAlertNotification: AlertNotification) :
    AlertHnadler(mAlertRule, mAlertNotification) {
    override fun check(alertBean: AlertBean) {
        if (alertBean.timeoutCount > mAlertRule.getMatchRules(alertBean.api)!!.mMaxTimeoutCount ?: 1) {
            Log.d("Alert--", "timeoutCount warn  out !!!!")
            mAlertNotification.nitify("timeoutCount")
        }

    }
}