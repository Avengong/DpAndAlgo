package com.tencent.avengong.designpattern.design.structuretype.compose.company;

import java.util.ArrayList;
import java.util.List;

public class Department extends HumanResource {

    private List<HumanResource> mSubDepartments = new ArrayList<>();

    public Department(int mId) {
        super(mId);
    }


    @Override
    public long getSalary() {

        long salary = 0;
        for (HumanResource humanResource : mSubDepartments) {
            salary += humanResource.getSalary();
        }
        mSalary = salary;
        return salary;
    }


    public void addHuman(HumanResource fileSystemNode) {
        mSubDepartments.add(fileSystemNode);
    }

    public void removeHuman(HumanResource node) {
        int size = mSubDepartments.size();
        int i = 0;
        for (; i < size; i++) {
            if (mSubDepartments.get(i).getmId() == (node.getmId())) {
                break;
            }
        }
        //这循环操作很6啊
        if (i < size) {
            mSubDepartments.remove(i);
        }
    }
}
