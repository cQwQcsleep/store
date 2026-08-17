package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface MethodInvocationTree extends ExpressionTree {
    List<? extends ExpressionTree> getArguments();

    ExpressionTree getMethodSelect();

    List<? extends Tree> getTypeArguments();
}
