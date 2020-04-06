package com.tencent.avengong.algorithm.stack;

/**
 * 基于链表实现的栈
 */
public class LinkNodeStack {

    private LinkNode mHead;
    private LinkNode mTail;
    private LinkNode mGuard;  //哨兵头-带头链表

    public LinkNodeStack() {
        mHead = null;
    }

    public static void main(String[] args) {
        LinkNodeStack linkNodeStack = new LinkNodeStack();
        linkNodeStack.push(100);
        linkNodeStack.push(101);
        linkNodeStack.push(102);
//        linkNodeStack.push(103);
//        linkNodeStack.push(103);
//        linkNodeStack.push(103);
//        linkNodeStack.push(103);


        Object pop1 = linkNodeStack.pop();
        Object pop2 = linkNodeStack.pop();
        Object pop3 = linkNodeStack.pop();
        Object pop4 = linkNodeStack.pop();
        System.out.println(" pop:" + pop1 + "pop:" + pop2 + "pop:" + pop3 + "pop4:" + pop4);
    }

    public boolean push(Object object) {

        if (mHead == null) {
            mHead = new LinkNode(object, null);
            mHead.setmData(object);
            return true;

        }
        LinkNode newNode = new LinkNode(object, null);
        newNode.setmNext(mHead);
        mHead = newNode;

        return true;
    }

    public Object pop() {
        if (mHead == null) {
            return null;
        }
        if (mHead.getmNext() == null) {
            Object o = mHead.getmData();
            mHead = null;
            return o;
        }
        Object temp;
        temp = mHead.getmData();
        mHead = mHead.getmNext();
        return temp;

    }

}
