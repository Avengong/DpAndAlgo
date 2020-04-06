package com.tencent.avengong.designpattern.design.创建型.factory.factorymethod

import com.tencent.avengong.designpattern.design.创建型.factory.parser.IRuleConfigParser
import com.tencent.avengong.designpattern.design.创建型.factory.parser.YamlRuleConfigParser

class YamlParserFactory : AbsIParserFactory {
    override fun createConfigParser(): IRuleConfigParser? {

        return YamlRuleConfigParser()
    }

    override fun createSystemParser(): IRuleConfigParser? {

        return null
    }
}