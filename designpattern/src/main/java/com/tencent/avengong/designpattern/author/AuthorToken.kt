package com.tencent.avengong.designpattern.author

class AuthorToken {

    private var mToken: String
    private var mCreateTime: Long
    private var mExpireTimeInterval: Long = DEFAULT_EXPIRE_INTERVAL

    constructor(token: String, createTime: Long) {
        this.mToken = token
        this.mCreateTime = createTime
    }

    companion object {
        val DEFAULT_EXPIRE_INTERVAL: Long = 1000 * 60

        /**
         *客户端和服务端都可以使用
         */
        fun createAuthorToken(url: String, createTime: Long, params: Map<String, String>): AuthorToken {

            var token = "$url?"
            for ((key, value) in params) {
                token = "$token&$key=$value"
            }
            token = "$token&t=$createTime"
            return AuthorToken(token, createTime)
        }

    }

    fun getToken(): String {

        return mToken
    }

    fun isExpire(): Boolean {

        return System.currentTimeMillis() - mCreateTime > DEFAULT_EXPIRE_INTERVAL
    }


    fun isMatch(token: AuthorToken): Boolean {
        return token.mToken == this.mToken
    }


}