package com.sun.source.tree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ConditionalExpressionTree extends ExpressionTree {
    ExpressionTree getCondition();

    ExpressionTree getFalseExpression();

    ExpressionTree getTrueExpression();
}
