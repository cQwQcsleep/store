package com.sun.source.tree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface InstanceOfTree extends ExpressionTree {
    ExpressionTree getExpression();

    PatternTree getPattern();

    Tree getType();
}
