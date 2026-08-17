package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface BlockTree extends StatementTree {
    List<? extends StatementTree> getStatements();

    boolean isStatic();
}
