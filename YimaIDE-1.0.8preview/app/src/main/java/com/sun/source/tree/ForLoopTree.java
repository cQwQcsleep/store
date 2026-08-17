package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ForLoopTree extends StatementTree {
    ExpressionTree getCondition();

    List<? extends StatementTree> getInitializer();

    StatementTree getStatement();

    List<? extends ExpressionStatementTree> getUpdate();
}
