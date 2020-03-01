package com.tencent.avengong.designpattern.ocp.handler

import com.tencent.avengong.designpattern.ocp.AlertBean
import com.tencent.avengong.designpattern.ocp.AlertNotification
import com.tencent.avengong.designpattern.ocp.AlertRule

abstract class AlertHnadler {

    var mAlertRule: AlertRule
    var mAlertNotification: AlertNotification

    constructor(mAlertRule: AlertRule, mAlertNotification: AlertNotification) {
        this.mAlertRule = mAlertRule
        this.mAlertNotification = mAlertNotification
    }

    public abstract fun check(alertBean: AlertBean)
}