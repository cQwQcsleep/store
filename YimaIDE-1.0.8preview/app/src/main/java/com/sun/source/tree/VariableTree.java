package com.sun.source.tree;

import javax.lang.model.element.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface VariableTree extends StatementTree {
    ExpressionTree getInitializer();

    ModifiersTree getModifiers();

    Name getName();

    ExpressionTree getNameExpression();

    Tree getType();
}
