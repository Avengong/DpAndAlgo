package com.tencent.avengong.algorithm.stack;

/**
 * 基于数组实现的栈
 * 支持动态扩容
 */
public class ArrayStack {

    private Object[] mItems;
    private int mCount;
    private int mSize;


    public ArrayStack(int mSize) {
        this.mSize = mSize;
        mCount = 0;
        mItems = new Object[mSize];

    }

    public static void main(String[] args) {

        ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);

        Object pop = arrayStack.pop();
        Object pop2 = arrayStack.pop();
        Object pop3 = arrayStack.pop();
        Object pop4 = arrayStack.pop();
        System.out.println(" pop:" + pop + "pop:" + pop2 + "pop:" + pop3 + "pop4:" + pop4);
    }

    public boolean push(Object str) {
        if (mCount == mSize) {
            //动态扩容: 1,arraylist；2，自己手动扩容
            Object[] src = mItems;
            mItems = new Object[(int) (mSize * 1.5)];
            System.arraycopy(src, 0, mItems, 0, mSize);
            System.out.println(" push- 扩容-length:" + mSize + " newlen:" + mItems.length);
            mSize = mItems.length;
            src = null;
        }

        System.out.println(" push--mcount:" + mCount);
        mItems[mCount] = str;
        mCount++;
        return true;
    }

    public Object pop() {
        System.out.println(" pop---mcount:" + mCount);
        if (mCount <= 0) {
            return null;
        }
        Object pop;
        pop = mItems[mCount - 1];
        mCount--;

        return pop;

    }

}
