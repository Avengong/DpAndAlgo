package com.tencent.avengong.designpattern.design.structuretype.adapter;

public class AdaptorObjectImpl implements ITarget {

    Adaptee mAdaptee;

    public AdaptorObjectImpl(Adaptee mAdaptee) {
        this.mAdaptee = mAdaptee;
    }

    @Override
    public void f1() {
        mAdaptee.a();
    }

    @Override
    public void f2() {
        mAdaptee.b();
    }
}
