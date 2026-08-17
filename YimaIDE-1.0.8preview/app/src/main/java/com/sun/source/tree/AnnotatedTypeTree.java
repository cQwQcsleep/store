package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface AnnotatedTypeTree extends ExpressionTree {
    List<? extends AnnotationTree> getAnnotations();

    ExpressionTree getUnderlyingType();
}
