package com.sun.source.tree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface RequiresTree extends DirectiveTree {
    ExpressionTree getModuleName();

    boolean isStatic();

    boolean isTransitive();
}
