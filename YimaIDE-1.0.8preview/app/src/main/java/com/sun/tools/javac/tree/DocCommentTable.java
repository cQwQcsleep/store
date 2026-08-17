package com.sun.tools.javac.tree;

import com.sun.source.doctree.DocCommentTree;
import com.sun.tools.javac.parser.Tokens;
import javax.lang.model.util.Elements;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DocCommentTable {
    Tokens.Comment getComment(JCTree jCTree);

    Elements.DocCommentKind getCommentKind(JCTree jCTree);

    String getCommentText(JCTree jCTree);

    DocCommentTree getCommentTree(JCTree jCTree);

    boolean hasComment(JCTree jCTree);

    void putComment(JCTree jCTree, Tokens.Comment comment);
}
