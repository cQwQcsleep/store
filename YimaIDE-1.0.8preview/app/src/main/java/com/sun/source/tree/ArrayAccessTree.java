package com.sun.source.tree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ArrayAccessTree extends ExpressionTree {
    ExpressionTree getExpression();

    ExpressionTree getIndex();
}
