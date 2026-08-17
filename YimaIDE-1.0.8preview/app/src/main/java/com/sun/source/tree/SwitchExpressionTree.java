package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface SwitchExpressionTree extends ExpressionTree {
    List<? extends CaseTree> getCases();

    ExpressionTree getExpression();
}
