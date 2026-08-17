package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ProvidesTree extends DirectiveTree {
    List<? extends ExpressionTree> getImplementationNames();

    ExpressionTree getServiceName();
}
