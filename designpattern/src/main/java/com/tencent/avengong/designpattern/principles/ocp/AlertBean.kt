package com.tencent.avengong.designpattern.principles.ocp

class AlertBean {

    var api: String

    constructor(api: String, errorCount: Int, requestCount: Int, timeCount: Int, durationOfSeconds: Int) {
        this.api = api
        this.errorCount = errorCount
        this.requestCount = requestCount
        this.timeoutCount = timeCount
        this.durationOfSeconds = durationOfSeconds
    }

    var errorCount: Int
    var requestCount: Int
    var timeoutCount: Int
    var durationOfSeconds: Int


}