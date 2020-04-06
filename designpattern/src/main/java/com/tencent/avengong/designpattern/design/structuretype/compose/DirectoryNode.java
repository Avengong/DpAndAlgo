package com.tencent.avengong.designpattern.design.structuretype.compose;

import java.util.ArrayList;
import java.util.List;

public class DirectoryNode extends FileSystemNode {
    private List<FileSystemNode> mSubNodes = new ArrayList<>();

    public DirectoryNode(String mPath) {
        super(mPath);
    }

    public void addNode(FileSystemNode fileSystemNode) {
        mSubNodes.add(fileSystemNode);
    }

    public void removeNode(FileSystemNode node) {
        int size = mSubNodes.size();
        int i = 0;
        for (; i < size; i++) {
            if (mSubNodes.get(i).mPath.equalsIgnoreCase(node.mPath)) {
                break;
            }
        }
        //这循环操作很6啊
        if (i < size) {
            mSubNodes.remove(i);
        }
    }

    @Override
    public long getNodeSize() {
        long nodeSize = 0;
        for (FileSystemNode node : mSubNodes) {
            nodeSize += node.getNodeSize();
        }


        return nodeSize;
    }

    @Override
    public int getNodeCount() {
        int count = 0;
        for (FileSystemNode node : mSubNodes) {
            count += node.getNodeCount();

        }

        return count;
    }

}
