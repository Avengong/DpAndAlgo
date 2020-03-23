package com.tencent.avengong.designpattern.principles.ocp.handler

import com.tencent.avengong.designpattern.principles.ocp.AlertBean
import com.tencent.avengong.designpattern.principles.ocp.AlertNotification
import com.tencent.avengong.designpattern.principles.ocp.AlertRule

abstract class AlertHnadler {

    var mAlertRule: AlertRule
    var mAlertNotification: AlertNotification

    constructor(mAlertRule: AlertRule, mAlertNotification: AlertNotification) {
        this.mAlertRule = mAlertRule
        this.mAlertNotification = mAlertNotification
    }

    public abstract fun check(alertBean: AlertBean)
}