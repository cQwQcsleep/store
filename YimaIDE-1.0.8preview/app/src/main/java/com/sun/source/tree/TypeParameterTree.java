package com.sun.source.tree;

import java.util.List;
import javax.lang.model.element.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface TypeParameterTree extends Tree {
    List<? extends AnnotationTree> getAnnotations();

    List<? extends Tree> getBounds();

    Name getName();
}
