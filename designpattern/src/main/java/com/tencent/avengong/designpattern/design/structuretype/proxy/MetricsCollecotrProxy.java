package com.tencent.avengong.designpattern.design.structuretype.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MetricsCollecotrProxy {

    MetricsCollector metricsCollector;

    public MetricsCollecotrProxy() {
        this.metricsCollector = new MetricsCollector();
    }

    public Object createProxy(Object object) {

        Class<?>[] interfaces = object.getClass().getInterfaces();
        ProxyHandler proxyHandler = new ProxyHandler(object);
        Object o = Proxy.newProxyInstance(object.getClass().getClassLoader(), interfaces, proxyHandler);
        return o;


    }

    class ProxyHandler implements InvocationHandler {

        private Object object;

        public ProxyHandler(Object object) {

            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            //这句话就是用来触发被代理类方法的
            Object invoke = method.invoke(object, args);

            String name = method.getName();
            String string = String.format("proxy   name:%s, proxyname:%s,  objectName:%s", name, proxy.getClass().getName(), object.getClass().getName());

            if (name.equals("login")) {
                System.out.println("login ---proxy, go ");
                return null;
            }
            if (name.equals("register")) {
                System.out.println("register ---proxy, go ");
                return null;
            }

            System.out.println(string);

            return null;
        }
    }

}
