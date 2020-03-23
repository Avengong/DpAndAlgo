package com.tencent.avengong.designpattern.principles.ocp.handler

import android.util.Log
import com.tencent.avengong.designpattern.principles.ocp.AlertBean
import com.tencent.avengong.designpattern.principles.ocp.AlertNotification
import com.tencent.avengong.designpattern.principles.ocp.AlertRule

class TimeOuntHandler(mAlertRule: AlertRule, mAlertNotification: AlertNotification) :
    AlertHnadler(mAlertRule, mAlertNotification) {
    override fun check(alertBean: AlertBean) {
        if (alertBean.timeoutCount > mAlertRule.getMatchRules(alertBean.api)!!.mMaxTimeoutCount ?: 1) {
            Log.d("Alert--", "timeoutCount warn  out !!!!")
            mAlertNotification.nitify("timeoutCount")
        }

    }
}