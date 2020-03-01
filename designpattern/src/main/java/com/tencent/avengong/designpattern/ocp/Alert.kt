package com.tencent.avengong.designpattern.ocp

import com.tencent.avengong.designpattern.ocp.handler.AlertHnadler

class Alert {


    val alertHnadlers = arrayListOf<AlertHnadler>()

    fun registerHnadler(alertHnadler: AlertHnadler) {
        alertHnadlers.add(alertHnadler)

    }


    fun check(alertBean: AlertBean) {

        for (i in 0 until alertHnadlers.size) {
            alertHnadlers[i].check(alertBean)

        }


    }

}


