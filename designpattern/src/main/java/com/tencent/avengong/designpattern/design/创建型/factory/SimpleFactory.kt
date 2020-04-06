package com.tencent.avengong.designpattern.design.创建型.factory

import com.tencent.avengong.designpattern.design.创建型.factory.parser.*

class SimpleFactory {

    companion object {
        @JvmStatic
        fun createParse(ruleConfigFilePath: String, ruleConfigFileExtension: String): IRuleConfigParser? {

            var parser: IRuleConfigParser?
            if ("json".equals(ruleConfigFileExtension, ignoreCase = true)) {
                parser = JsonRuleConfigParser()
            } else if ("xml".equals(ruleConfigFileExtension, ignoreCase = true)) {
                parser = XmlRuleConfigParser()
            } else if ("yaml".equals(ruleConfigFileExtension, ignoreCase = true)) {
                parser = YamlRuleConfigParser()
            } else if ("properties".equals(ruleConfigFileExtension, ignoreCase = true)) {
                parser = PropertiesRuleConfigParser()
            } else {
                throw RuntimeException(
                    "Rule config file format is not supported: $ruleConfigFilePath"
                )
            }
            return parser
        }

    }


}