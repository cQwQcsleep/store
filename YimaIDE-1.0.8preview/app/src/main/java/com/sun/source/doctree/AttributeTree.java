package com.sun.source.doctree;

import java.util.List;
import javax.lang.model.element.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface AttributeTree extends DocTree {

    public enum ValueKind {
        EMPTY,
        UNQUOTED,
        SINGLE,
        DOUBLE
    }

    Name getName();

    List<? extends DocTree> getValue();

    ValueKind getValueKind();
}
