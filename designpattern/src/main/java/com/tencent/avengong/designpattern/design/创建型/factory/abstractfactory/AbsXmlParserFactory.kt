package com.tencent.avengong.designpattern.design.创建型.factory.factorymethod

import com.tencent.avengong.designpattern.design.创建型.factory.parser.IRuleConfigParser
import com.tencent.avengong.designpattern.design.创建型.factory.parser.XmlRuleConfigParser

class AbsXmlParserFactory : AbsIParserFactory {
    override fun createConfigParser(): IRuleConfigParser? {

        return XmlRuleConfigParser()
    }

    override fun createSystemParser(): IRuleConfigParser? {

        return null
    }
}