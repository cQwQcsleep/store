package com.sun.source.tree;

import javax.lang.model.element.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LabeledStatementTree extends StatementTree {
    Name getLabel();

    StatementTree getStatement();
}
