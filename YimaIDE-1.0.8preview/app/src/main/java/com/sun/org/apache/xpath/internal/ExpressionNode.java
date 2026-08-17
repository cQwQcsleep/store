package com.sun.org.apache.xpath.internal;

import javax.xml.transform.SourceLocator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ExpressionNode extends SourceLocator {
    void exprAddChild(ExpressionNode expressionNode, int i);

    ExpressionNode exprGetChild(int i);

    int exprGetNumChildren();

    ExpressionNode exprGetParent();

    void exprSetParent(ExpressionNode expressionNode);
}
