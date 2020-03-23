package com.tencent.avengong.designpattern.samples.apiauthor

interface IAppKeyStorage {

    fun getAppKey(appid: String): String
}