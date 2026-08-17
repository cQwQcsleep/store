package com.sun.tools.javac.parser;

import com.sun.tools.javac.tree.JCTree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Parser {
    JCTree.JCCompilationUnit parseCompilationUnit();

    JCTree.JCExpression parseExpression();

    JCTree.JCStatement parseStatement();

    JCTree.JCExpression parseType();
}
