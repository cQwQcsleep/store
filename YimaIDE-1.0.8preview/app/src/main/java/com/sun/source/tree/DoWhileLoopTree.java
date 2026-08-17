package com.sun.source.tree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DoWhileLoopTree extends StatementTree {
    ExpressionTree getCondition();

    StatementTree getStatement();
}
