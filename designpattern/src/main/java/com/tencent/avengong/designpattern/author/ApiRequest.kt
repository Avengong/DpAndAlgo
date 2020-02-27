package com.tencent.avengong.designpattern.author

class ApiRequest {

    var mBaseUrl: String
    var mAppid: String
    var mToken: String
    var mTimeStamp: Long

    constructor(mBaseUrl: String, mAppid: String, mToken: String, mTimeStamp: Long) {
        this.mBaseUrl = mBaseUrl
        this.mAppid = mAppid
        this.mToken = mToken
        this.mTimeStamp = mTimeStamp
    }

    companion object {
        fun buildFullFromUrl(url: String): ApiRequest {

//            http@ //www.baidu.com?appid=4444&token=http://www.baidu.com?&4444=key&t=1582790752284&t=1582790752284

            val split2 = url.split("token=")

            val split = split2[0].split("?")
            val split1 = split[1].split("&")
            val split3 = split2[1].split("&t=")
            return ApiRequest(split[0], split1[0], split3[0], split3[1].toLong())

        }
    }

    fun createFullUrl(): String {

        return "$mBaseUrl?appid=$mAppid&token=$mToken&t=$mTimeStamp"
    }

}