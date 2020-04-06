package com.tencent.avengong.designpattern.design.创建型.factory.factorymethod

import com.tencent.avengong.designpattern.design.创建型.factory.parser.IRuleConfigParser
import java.util.*

class FactoryMethod {

    companion object {


        internal var mfactorys: MutableMap<String, AbsIParserFactory> = HashMap()

        init {
            mfactorys["json"] = AbsJsonParserFactory()

        }

        @JvmStatic
        fun createParser(ruleConfigFilePath: String, ruleConfigFileExtension: String): IRuleConfigParser? {

            when {
//                "json".equals(ruleConfigFileExtension,ignoreCase = true) ->{
//                    if (mfactorys["json"] ==null){
//                        mfactorys["json"]=AbsJsonParserFactory()
//                    }
//                }
//                "xml".equals(ruleConfigFileExtension,ignoreCase = true) -> parserFactory=AbsXmlParserFactory()
//                "properties".equals(ruleConfigFileExtension,ignoreCase = true) -> parserFactory=AbsPropertyParserFactory()
//                "yaml".equals(ruleConfigFileExtension,ignoreCase = true) -> parserFactory=AbsYamlParserFactory()
//                "xxx".equals(ruleConfigFileExtension,ignoreCase = true) -> parserFactory=AbsYamlParserFactory()

            }

            var parserFactory: AbsIParserFactory? = mfactorys[ruleConfigFileExtension]
            return parserFactory?.createConfigParser()

        }
    }

}