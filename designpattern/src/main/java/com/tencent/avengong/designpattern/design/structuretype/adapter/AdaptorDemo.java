package com.tencent.avengong.designpattern.design.structuretype.adapter;

public class AdaptorDemo {

    public static void main(String[] args) {
        AdaptorClazzImpl adaptorClazz = new AdaptorClazzImpl();
        adaptorClazz.f1();
        adaptorClazz.f2();

        AdaptorObjectImpl adaptorObject = new AdaptorObjectImpl(new Adaptee());

        adaptorObject.f1();
        adaptorObject.f2();

    }
}
