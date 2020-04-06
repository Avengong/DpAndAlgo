package com.tencent.avengong.designpattern.design.structuretype.compose;

/**
 * 组合模式下的文件系统目录
 * 1，支持增删某个目录
 * 2，获取某个子目录的大小
 * 3，获取某个子目录的文件个数
 */
public abstract class FileSystemNode {

    protected String mPath;

    public FileSystemNode(String mPath) {
        this.mPath = mPath;
    }

    public abstract long getNodeSize();

    public abstract int getNodeCount();

}
