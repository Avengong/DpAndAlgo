package com.tencent.avengong.designpattern.design;

import com.tencent.avengong.designpattern.design.创建型.单例.IdGeneratorInstance;

public class Dtest {
    public static void main(String[] ar) {
        IdGeneratorInstance.INSTANCE.getId();
    }
}
