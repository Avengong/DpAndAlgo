package com.tencent.avengong.designpattern.design.structuretype.compose.company;

public abstract class HumanResource {
    protected long mSalary;
    private int mId;

    public HumanResource(int mId) {
        this.mId = mId;
    }

    public int getmId() {

        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public long getmSalary() {
        return mSalary;
    }

    public void setmSalary(long mSalary) {
        this.mSalary = mSalary;
    }

    public abstract long getSalary();

}
