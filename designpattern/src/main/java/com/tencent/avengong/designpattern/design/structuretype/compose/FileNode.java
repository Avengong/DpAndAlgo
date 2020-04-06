package com.tencent.avengong.designpattern.design.structuretype.compose;

import java.io.File;

public class FileNode extends FileSystemNode {


    public FileNode(String mPath) {
        super(mPath);
    }

    @Override
    public long getNodeSize() {
        File file = new File(mPath);
        if (!file.exists()) {
            return 0;
        }
        return file.length();
    }

    @Override
    public int getNodeCount() {
        return 1;
    }
}
