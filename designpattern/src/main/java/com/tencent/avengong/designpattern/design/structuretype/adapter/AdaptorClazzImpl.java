package com.tencent.avengong.designpattern.design.structuretype.adapter;

public class AdaptorClazzImpl extends Adaptee implements ITarget {


    @Override
    public void f1() {
        a();
    }

    @Override
    public void f2() {
        b();
    }
}
