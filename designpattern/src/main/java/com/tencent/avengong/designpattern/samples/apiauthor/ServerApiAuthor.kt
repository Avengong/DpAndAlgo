package com.tencent.avengong.designpattern.samples.apiauthor

class ServerApiAuthor : IApiAuthor {

    var mAppKeyStorage: IAppKeyStorage

    constructor(iAppKeyStorage: IAppKeyStorage) {
        this.mAppKeyStorage = iAppKeyStorage
    }

    override fun doAuthor(url: String) {

        val clientCertRequest = ApiRequest.parseRequestUrl(url)
        val clientAuthorToken = AuthorToken(clientCertRequest.mToken, clientCertRequest.mTimeStamp)

        val serverToken = AuthorToken.createToken(
            clientCertRequest.mBaseUrl,
            System.currentTimeMillis(),
            hashMapOf(clientCertRequest.mAppId to mAppKeyStorage.getAppKey(clientCertRequest.mAppId))
        )

        if (clientAuthorToken.isInvalid()) {

        }

        if (clientAuthorToken.isMatch(serverToken)) {

        }


    }
}