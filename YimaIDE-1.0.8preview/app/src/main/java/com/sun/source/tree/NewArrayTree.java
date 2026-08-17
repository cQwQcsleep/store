package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface NewArrayTree extends ExpressionTree {
    List<? extends AnnotationTree> getAnnotations();

    List<? extends List<? extends AnnotationTree>> getDimAnnotations();

    List<? extends ExpressionTree> getDimensions();

    List<? extends ExpressionTree> getInitializers();

    Tree getType();
}
