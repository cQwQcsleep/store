package com.sun.source.tree;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Scope {
    TypeElement getEnclosingClass();

    ExecutableElement getEnclosingMethod();

    Scope getEnclosingScope();

    Iterable<? extends Element> getLocalElements();
}
