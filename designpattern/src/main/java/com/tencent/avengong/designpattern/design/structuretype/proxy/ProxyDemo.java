package com.tencent.avengong.designpattern.design.structuretype.proxy;

public class ProxyDemo {

    public static void main(String[] a) {
//        IUserAthor userAhthorProxy = new UserAhthorProxy(new UserAuthorMgr());

//        userAhthorProxy.register();
//        userAhthorProxy.login();

        //动态代理

        MetricsCollecotrProxy metricsCollecotrProxy = new MetricsCollecotrProxy();
        IUserAthor proxy = (IUserAthor) metricsCollecotrProxy.createProxy(new UserAuthorMgr());
        proxy.login();
        proxy.register();

    }
}
