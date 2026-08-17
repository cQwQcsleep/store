package com.sun.source.tree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface EnhancedForLoopTree extends StatementTree {
    ExpressionTree getExpression();

    StatementTree getStatement();

    VariableTree getVariable();
}
