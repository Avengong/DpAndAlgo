package com.tencent.avengong.designpattern.design.创建型.单例;

import java.util.concurrent.atomic.AtomicLong;

public enum IdGeneratorInstance {

    INSTANCE;

    private AtomicLong idGene = new AtomicLong();

    public long getId() {
        return idGene.getAndIncrement();
    }

}
