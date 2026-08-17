package com.sun.source.doctree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ThrowsTree extends BlockTagTree {
    List<? extends DocTree> getDescription();

    ReferenceTree getExceptionName();
}
