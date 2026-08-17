package com.sun.source.doctree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ReturnTree extends BlockTagTree, InlineTagTree {
    List<? extends DocTree> getDescription();

    default boolean isInline() {
        return false;
    }
}
