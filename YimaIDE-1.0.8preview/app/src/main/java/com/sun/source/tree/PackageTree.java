package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface PackageTree extends Tree {
    List<? extends AnnotationTree> getAnnotations();

    ExpressionTree getPackageName();
}
