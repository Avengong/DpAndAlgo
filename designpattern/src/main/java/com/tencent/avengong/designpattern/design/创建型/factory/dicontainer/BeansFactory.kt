package com.tencent.avengong.designpattern.design.创建型.factory.dicontainer

import java.lang.reflect.InvocationTargetException

class BeansFactory {

    val singletonObjects = mutableMapOf<String, Any>()
    val beansDefinitions = mutableMapOf<String, BeansDifinition>()


    fun addBeansDifinition(parseConfig: MutableList<BeansDifinition>) {

        for (beanDefinition in parseConfig) {

            beansDefinitions[beanDefinition.id] = beanDefinition

            if (beanDefinition.isInstance() && !beanDefinition.layInit) {
                createBean(beanDefinition)

            }

        }

    }


    fun getBean(id: String): Any? {
        val beansDifinition = beansDefinitions[id] ?: throw RuntimeException("beans is not load!!")

        return createBean(beansDifinition)
    }


    private fun createBean(beansDifinition: BeansDifinition): Any? {

        var `object`: Any? = null
        try {
            val clazz = Class.forName(beansDifinition.className)

            val constructAgrs = beansDifinition.constructAgrs
            val size = constructAgrs.size
            if (size > 0) {
                val clazzes = arrayOfNulls<Class<*>>(size)
                val objects = arrayOfNulls<Any>(size)
                for (i in 0 until size) {
                    val agr = constructAgrs[i]
                    if (!agr.isRef) {
                        //构造参数对象没有引用其他对象
                        clazzes[i] = agr.clazz
                        objects[i] = agr.mObject
                    } else {
                        //引用的对象还引用了其他的对象
                        //如果引用了其他对象，那么就设置这个object为string
                        val referDifinition = beansDefinitions[agr.mObject]
                        val aClass = Class.forName(referDifinition!!.className)
                        clazzes[i] = aClass
                        objects[i] = createBean(referDifinition)

                    }
                }

                `object` = clazz.getConstructor(*clazzes).newInstance(*objects)
            } else {

                `object` = clazz.newInstance()

            }

            return `object`

        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }

        return null

    }


}