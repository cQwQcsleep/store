package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LambdaExpressionTree extends ExpressionTree {

    public enum BodyKind {
        EXPRESSION,
        STATEMENT
    }

    Tree getBody();

    BodyKind getBodyKind();

    List<? extends VariableTree> getParameters();
}
