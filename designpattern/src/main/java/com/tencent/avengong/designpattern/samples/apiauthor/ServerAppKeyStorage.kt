package com.tencent.avengong.designpattern.samples.apiauthor

class ServerAppKeyStorage : IAppKeyStorage {
    override fun getAppKey(appid: String): String {

        return "这是服务端保存的appkey"
    }
}