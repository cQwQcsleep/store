package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ModuleTree extends Tree {

    public enum ModuleKind {
        OPEN,
        STRONG
    }

    List<? extends AnnotationTree> getAnnotations();

    List<? extends DirectiveTree> getDirectives();

    ModuleKind getModuleType();

    ExpressionTree getName();
}
