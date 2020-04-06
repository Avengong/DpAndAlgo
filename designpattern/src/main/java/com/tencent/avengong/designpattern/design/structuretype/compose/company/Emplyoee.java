package com.tencent.avengong.designpattern.design.structuretype.compose.company;

public class Emplyoee extends HumanResource {

    public Emplyoee(int mId, int salary) {
        super(mId);
        mSalary = salary;
    }

    @Override
    public long getSalary() {
        return mSalary;
    }
}
