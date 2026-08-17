package org.joni;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
final class CaptureTreeNode {
    static final int HISTORY_TREE_INIT_ALLOC_SIZE = 8;
    CaptureTreeNode[] children;
    int numChildren;
    int beg = -1;
    int end = -1;
    int group = -1;

    public void addChild(CaptureTreeNode captureTreeNode) {
        CaptureTreeNode[] captureTreeNodeArr = this.children;
        if (captureTreeNodeArr == null) {
            this.children = new CaptureTreeNode[8];
        } else if (this.numChildren >= captureTreeNodeArr.length) {
            CaptureTreeNode[] captureTreeNodeArr2 = new CaptureTreeNode[captureTreeNodeArr.length << 1];
            System.arraycopy(captureTreeNodeArr, 0, captureTreeNodeArr2, 0, captureTreeNodeArr.length);
            this.children = captureTreeNodeArr2;
        }
        CaptureTreeNode[] captureTreeNodeArr3 = this.children;
        int i = this.numChildren;
        captureTreeNodeArr3[i] = captureTreeNode;
        this.numChildren = i + 1;
    }

    public void clear() {
        for (int i = 0; i < this.numChildren; i++) {
            this.children[i] = null;
        }
        this.numChildren = 0;
        this.end = -1;
        this.beg = -1;
        this.group = -1;
    }

    public CaptureTreeNode cloneTree() {
        CaptureTreeNode captureTreeNode = new CaptureTreeNode();
        captureTreeNode.beg = this.beg;
        captureTreeNode.end = this.end;
        for (int i = 0; i < this.numChildren; i++) {
            captureTreeNode.addChild(this.children[i].cloneTree());
        }
        return captureTreeNode;
    }
}
