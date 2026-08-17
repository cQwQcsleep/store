package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface NewClassTree extends ExpressionTree {
    List<? extends ExpressionTree> getArguments();

    ClassTree getClassBody();

    ExpressionTree getEnclosingExpression();

    ExpressionTree getIdentifier();

    List<? extends Tree> getTypeArguments();
}
