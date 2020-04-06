package com.tencent.avengong.designpattern.design.创建型.factory.dicontainer

interface IDiContainer {

    fun getBean(id: String): Any?
}