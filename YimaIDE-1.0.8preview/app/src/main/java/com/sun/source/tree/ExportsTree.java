package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ExportsTree extends DirectiveTree {
    List<? extends ExpressionTree> getModuleNames();

    ExpressionTree getPackageName();
}
