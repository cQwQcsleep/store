package com.sun.source.tree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ImportTree extends Tree {
    Tree getQualifiedIdentifier();

    boolean isModule();

    boolean isStatic();
}
