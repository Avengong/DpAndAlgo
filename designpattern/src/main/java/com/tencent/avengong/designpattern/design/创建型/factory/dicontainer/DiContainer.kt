package com.tencent.avengong.designpattern.design.创建型.factory.dicontainer

import android.content.Context
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class DiContainer : IDiContainer {

    lateinit var mBeansConfigParser: BeansConfigParser
    lateinit var mBeansFactory: BeansFactory

    constructor(context: Context) {
        mBeansConfigParser = BeansConfigParser()
        mBeansFactory = BeansFactory()
        loadBeansDefinition(context)

    }

    private fun loadBeansDefinition(context: Context) {

        val propertyFile = File("xxx.properties")
        var input: InputStream? = null
        try {
            input = BufferedInputStream(FileInputStream(propertyFile))
            val parseConfig = mBeansConfigParser.parseConfig(input)
            mBeansFactory.addBeansDifinition(parseConfig)
        } finally {
            try {
                input?.close()
            } catch (e: Exception) {

            }
        }


    }

    override fun getBean(id: String): Any? {
        return mBeansFactory.getBean(id)

    }
}