package com.tencent.avengong.designpattern.principles.ocp.handler

import android.util.Log
import com.tencent.avengong.designpattern.principles.ocp.AlertBean
import com.tencent.avengong.designpattern.principles.ocp.AlertNotification
import com.tencent.avengong.designpattern.principles.ocp.AlertRule

class ErrorCountHandler(mAlertRule: AlertRule, mAlertNotification: AlertNotification) :
    AlertHnadler(mAlertRule, mAlertNotification) {
    override fun check(alertBean: AlertBean) {
        if (alertBean.errorCount > mAlertRule.getMatchRules(alertBean.api)!!.mMaxErrorCount ?: 1) {
            Log.d("Alert--", "errortcount warn  out !!!!")
            mAlertNotification.nitify("errortcount")
        }

    }
}