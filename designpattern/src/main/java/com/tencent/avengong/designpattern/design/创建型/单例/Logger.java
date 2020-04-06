package com.tencent.avengong.designpattern.design.创建型.单例;

import java.util.concurrent.ConcurrentHashMap;

public class Logger {

    private static ConcurrentHashMap<String, Logger> conHashmap = new ConcurrentHashMap<String, Logger>();

    public Logger(String type) {

    }


    public Logger getInstance(String type) {

        conHashmap.put(type, new Logger(type));
        return conHashmap.get(type);
    }


}
