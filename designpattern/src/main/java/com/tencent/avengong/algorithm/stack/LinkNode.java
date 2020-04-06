package com.tencent.avengong.algorithm.stack;

/**
 * 链表
 */
public class LinkNode {

    private Object mData;
    private LinkNode mNext;

    public LinkNode(Object mData, LinkNode mNext) {
        this.mData = mData;
        this.mNext = mNext;
    }

    public LinkNode getmNext() {
        return mNext;
    }

    public void setmNext(LinkNode mNext) {
        this.mNext = mNext;
    }

    public Object getmData() {
        return mData;
    }

    public void setmData(Object mData) {
        this.mData = mData;
    }
}
