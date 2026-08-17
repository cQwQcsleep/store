package org.antlr.v4.runtime.tree;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface Tree {
    Tree getChild(int i);

    int getChildCount();

    Tree getParent();

    Object getPayload();

    String toStringTree();
}
