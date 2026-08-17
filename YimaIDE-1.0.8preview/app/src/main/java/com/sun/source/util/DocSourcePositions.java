package com.sun.source.util;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.tree.CompilationUnitTree;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DocSourcePositions extends SourcePositions {
    long getEndPosition(CompilationUnitTree compilationUnitTree, DocCommentTree docCommentTree, DocTree docTree);

    long getStartPosition(CompilationUnitTree compilationUnitTree, DocCommentTree docCommentTree, DocTree docTree);
}
