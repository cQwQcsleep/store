package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface OpensTree extends DirectiveTree {
    List<? extends ExpressionTree> getModuleNames();

    ExpressionTree getPackageName();
}
