package com.sun.tools.javac.tree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface EndPosTable {
    int getEndPos(JCTree jCTree);

    int replaceTree(JCTree jCTree, JCTree jCTree2);

    void setErrorEndPos(int i);

    <T extends JCTree> T storeEnd(T t, int i);
}
