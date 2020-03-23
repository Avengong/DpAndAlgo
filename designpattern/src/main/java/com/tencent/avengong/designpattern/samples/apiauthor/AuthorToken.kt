package com.tencent.avengong.designpattern.samples.apiauthor

class AuthorToken {


    var mToken: String
    var mCreateTime: Long
    var mExpireTime = DEFAULT_BUFFER_SIZE

    constructor(mToken: String, mCreateTime: Long) : this(mToken, mCreateTime, DEFAULT_EXPIRE_TIME)

    constructor(mToken: String, mCreateTime: Long, mExpireTime: Int) {
        this.mToken = mToken
        this.mCreateTime = mCreateTime
        this.mExpireTime = mExpireTime
    }


    companion object {
        fun createToken(url: String, createTime: Long, params: Map<String, String>): AuthorToken {

            //省略创建token的规则....
            val serverAuthorToken = AuthorToken("$url=$createTime=appid= ", System.currentTimeMillis())
            return serverAuthorToken


        }

        const val DEFAULT_EXPIRE_TIME = 60 * 1000
    }


    fun isInvalid(): Boolean {
        return System.currentTimeMillis() - mCreateTime > mExpireTime
    }


    fun isMatch(authorToken: AuthorToken): Boolean {
        return authorToken.mToken == mToken
    }

}