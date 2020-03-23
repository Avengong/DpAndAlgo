package com.tencent.avengong.designpattern.samples.apistatistics

class UserAthor {

    var metricsCollect: MetricsCollect

    constructor(metricsCollect: MetricsCollect) {
        this.metricsCollect = metricsCollect
    }

    fun register(userInfo: UserInfo) {

        metricsCollect.recordRequestInfo(RequestInfo("register", 200, System.currentTimeMillis()))

    }


    fun login(phoneNum: String, pswd: String) {
        metricsCollect.recordRequestInfo(RequestInfo("login", 500, System.currentTimeMillis()))

    }


}