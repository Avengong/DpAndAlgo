package com.tencent.avengong.designpattern.design.创建型.factory.dicontainer

class BeansDifinition {

    lateinit var id: String
    lateinit var className: String
    var layInit: Boolean = false
    var scope: Scope = Scope.INSTANCE
    var constructAgrs = mutableListOf<ConstructorAgr>()


    fun isInstance(): Boolean {
        return scope == Scope.INSTANCE
    }

    enum class Scope {
        INSTANCE,
        PROTOTYPE
    }


    inner class ConstructorAgr {

        var isRef: Boolean = false
        var clazz: Class<*>? = null
        var mObject: Any? = null

    }

}
