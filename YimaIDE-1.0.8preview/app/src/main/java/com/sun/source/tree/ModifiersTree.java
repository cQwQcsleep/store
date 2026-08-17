package com.sun.source.tree;

import java.util.List;
import java.util.Set;
import javax.lang.model.element.Modifier;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ModifiersTree extends Tree {
    List<? extends AnnotationTree> getAnnotations();

    Set<Modifier> getFlags();
}
