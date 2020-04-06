package com.tencent.avengong.designpattern.design.创建型.factory.parser

import com.tencent.avengong.designpattern.design.创建型.factory.RuleConfig

interface IRuleConfigParser {

    fun parse(text: String): RuleConfig?
}