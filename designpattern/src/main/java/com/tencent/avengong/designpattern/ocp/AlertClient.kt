package com.tencent.avengong.designpattern.ocp

import com.tencent.avengong.designpattern.ocp.handler.ErrorCountHandler
import com.tencent.avengong.designpattern.ocp.handler.TimeOuntHandler
import com.tencent.avengong.designpattern.ocp.handler.TpsHandler

class AlertClient {

    fun check() {
        val alertRule = AlertRule(1, 10, 10)
        alertRule.registerAletRulues("www.baidu.com", alertRule)
        val alertNotification = AlertNotification()
        val alert = Alert()
        alert.registerHnadler(TpsHandler(alertRule, alertNotification))
        alert.registerHnadler(ErrorCountHandler(alertRule, alertNotification))
        alert.registerHnadler(TimeOuntHandler(alertRule, alertNotification))

        val alertBean = AlertBean("www.baidu.com", 20, 11, 11, 10)
        alert.check(alertBean)


    }
}