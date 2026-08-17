package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface CaseTree extends Tree {

    public enum CaseKind {
        STATEMENT,
        RULE
    }

    default Tree getBody() {
        return null;
    }

    default CaseKind getCaseKind() {
        return CaseKind.STATEMENT;
    }

    @Deprecated
    ExpressionTree getExpression();

    List<? extends ExpressionTree> getExpressions();

    ExpressionTree getGuard();

    List<? extends CaseLabelTree> getLabels();

    List<? extends StatementTree> getStatements();
}
