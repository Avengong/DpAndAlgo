package com.tencent.avengong.designpattern.design.创建型.factory.factorymethod

import com.tencent.avengong.designpattern.design.创建型.factory.parser.IRuleConfigParser
import com.tencent.avengong.designpattern.design.创建型.factory.parser.XxxConfigParser

class AbsXxxParserFactory : AbsIParserFactory {
    override fun createConfigParser(): IRuleConfigParser? {

        return XxxConfigParser()
    }

    override fun createSystemParser(): IRuleConfigParser? {

        return null
    }
}