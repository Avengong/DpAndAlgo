package com.tencent.avengong.designpattern.design.创建型.factory.factorymethod

import com.tencent.avengong.designpattern.design.创建型.factory.parser.IRuleConfigParser
import com.tencent.avengong.designpattern.design.创建型.factory.parser.JsonRuleConfigParser

class JsonParserFactory : AbsIParserFactory {

    override fun createConfigParser(): IRuleConfigParser? {

        return JsonRuleConfigParser()
    }

    override fun createSystemParser(): IRuleConfigParser? {

        return null
    }
}