package com.tencent.avengong.designpattern.design.创建型.factory.factorymethod

import com.tencent.avengong.designpattern.design.创建型.factory.parser.IRuleConfigParser
import com.tencent.avengong.designpattern.design.创建型.factory.parser.PropertiesRuleConfigParser

class AbsPropertyParserFactory : AbsIParserFactory {
    override fun createConfigParser(): IRuleConfigParser? {

        return PropertiesRuleConfigParser()
    }

    override fun createSystemParser(): IRuleConfigParser? {

        return null
    }
}