package com.tencent.avengong.designpattern.author

interface IAuthorExecutor {


    fun author(url: String)
    fun author(apiRequest: ApiRequest)
}