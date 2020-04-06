package com.tencent.avengong.algorithm.queue;

public class ArrayQueue {

    private Object[] mItems;
    private int mSize;
    private int mHead;
    private int mTail;

    public ArrayQueue(int capacity) {
        this.mSize = capacity;

        mItems = new Object[capacity];
    }

    public static void main(String[] args) {

        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        arrayQueue.enqueue(4);

        Object dequeue = arrayQueue.dequeue();
        boolean enqueue = arrayQueue.enqueue(4);

        Object dequeue1 = arrayQueue.dequeue();
        Object dequeue2 = arrayQueue.dequeue();
        Object dequeue3 = arrayQueue.dequeue();
        Object dequeue4 = arrayQueue.dequeue();
        System.out.println(" object:" + dequeue + enqueue);
        System.out.println(" object:" + dequeue1 + dequeue2 + dequeue3 + dequeue4);

    }

    public boolean enqueue(Object object) {

        if (mTail == mSize) {

            if (mHead == 0) {
                //表示队列插满了
                return false;
            }

            for (int i = mHead; i < mTail; i++) {
                mItems[i - mHead] = mItems[i];
            }

            mTail = mTail - mHead;
            mHead = 0;

        }
        mItems[mTail] = object;
        mTail++;

        return true;
    }

    public Object dequeue() {
        if (mHead == mSize) {
            return null;
        }
        Object mItem = mItems[mHead];
        mHead++;
        return mItem;
    }


}
