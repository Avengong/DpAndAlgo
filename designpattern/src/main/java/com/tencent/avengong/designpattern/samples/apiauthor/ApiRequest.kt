package com.tencent.avengong.designpattern.samples.apiauthor

class ApiRequest {

    var mBaseUrl: String
    var mToken: String
    var mAppId: String
    var mTimeStamp: Long

    constructor(mBaseUrl: String, mToken: String, mAppId: String, mTimeStamp: Long) {
        this.mBaseUrl = mBaseUrl
        this.mToken = mToken
        this.mAppId = mAppId
        this.mTimeStamp = mTimeStamp
    }


    fun createRequestUrl(): String {

        return "这是客户端访问创建的url：$mBaseUrl:$mToken:$mAppId:$mTimeStamp"

    }


    companion object {
        fun parseRequestUrl(url: String): ApiRequest {
            val apiRequest = ApiRequest("这是服务端解析访问的url$url", "token...", "appid..", 6666666)
            return apiRequest

        }
    }

}