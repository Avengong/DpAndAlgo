package com.tencent.avengong.designpattern.design.structuretype.proxy;

public class UserAhthorProxy implements IUserAthor {

    IUserAthor userAthor;
    private MetricsCollector metricsCollector;


    public UserAhthorProxy(IUserAthor userAthor) {
        this.userAthor = userAthor;
        metricsCollector = new MetricsCollector();

    }

    @Override
    public void register() {
        userAthor.register();
        metricsCollector.collecte("register");
    }

    @Override
    public void login() {
        userAthor.login();
        metricsCollector.collecte("login");
    }
}
