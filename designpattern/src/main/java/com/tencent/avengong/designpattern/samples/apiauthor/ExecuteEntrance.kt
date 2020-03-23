package com.tencent.avengong.designpattern.samples.apiauthor

/**
 * 上帝类
 */
class ExecuteEntrance {

    var iApiAuthor: IApiAuthor

    constructor(iApiAuthor: IApiAuthor) {
        this.iApiAuthor = iApiAuthor
    }

    fun startClientRequest() {

        val url: String = "www."
        val currentTimeMillis = System.currentTimeMillis()
        val clientToken = AuthorToken.createToken(
            url,
            currentTimeMillis,
            hashMapOf("clientappid" to "clientappkey")
        )

        val createRequestUrl = ApiRequest(
            "这是客户端访问创建的url：$url", clientToken.mToken, "clientappid..",
            currentTimeMillis
        )
        iApiAuthor.doAuthor(createRequestUrl.createRequestUrl())


    }

}