package com.tencent.avengong.designpattern.samples.apistatistics

class RequestInfo {

    var api: String
    var responseTime: Long
    var timeStamp: Long

    constructor(api: String, responseTime: Long, timeStamp: Long) {
        this.api = api
        this.responseTime = responseTime
        this.timeStamp = timeStamp
    }


}