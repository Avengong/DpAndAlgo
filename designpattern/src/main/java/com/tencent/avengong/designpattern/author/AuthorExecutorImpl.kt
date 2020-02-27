package com.tencent.avengong.designpattern.author

import android.util.Log

class AuthorExecutorImpl : IAuthorExecutor {

    var credentialStorage: CredentialStorage

    constructor(credentialStorage: CredentialStorage) {
        this.credentialStorage = credentialStorage
    }

    override fun author(url: String) {

        val buildFullFromUrl = ApiRequest.buildFullFromUrl(url)
        author(buildFullFromUrl)

    }

    override fun author(apiRequest: ApiRequest) {

        val authorToken = AuthorToken(apiRequest.mToken, apiRequest.mTimeStamp)
        val expire = authorToken.isExpire()
        if (expire) {


        }

        val serverToken = AuthorToken.createAuthorToken(
            apiRequest.mBaseUrl,
            System.currentTimeMillis(),
            mapOf(apiRequest.mAppid to credentialStorage.getAppKey(apiRequest.mAppid))
        )
        val match = serverToken.isMatch(authorToken)
        if (match) {


        }

        Log.d("author", "expire:$expire, math:$match")


    }
}