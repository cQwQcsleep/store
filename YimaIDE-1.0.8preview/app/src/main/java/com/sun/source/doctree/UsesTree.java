package com.sun.source.doctree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface UsesTree extends BlockTagTree {
    List<? extends DocTree> getDescription();

    ReferenceTree getServiceType();
}
