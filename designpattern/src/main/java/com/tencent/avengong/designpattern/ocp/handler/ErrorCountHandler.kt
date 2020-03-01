package com.tencent.avengong.designpattern.ocp.handler

import android.util.Log
import com.tencent.avengong.designpattern.ocp.AlertBean
import com.tencent.avengong.designpattern.ocp.AlertNotification
import com.tencent.avengong.designpattern.ocp.AlertRule

class ErrorCountHandler(mAlertRule: AlertRule, mAlertNotification: AlertNotification) :
    AlertHnadler(mAlertRule, mAlertNotification) {
    override fun check(alertBean: AlertBean) {
        if (alertBean.errorCount > mAlertRule.getMatchRules(alertBean.api)!!.mMaxErrorCount ?: 1) {
            Log.d("Alert--", "errortcount warn  out !!!!")
            mAlertNotification.nitify("errortcount")
        }

    }
}