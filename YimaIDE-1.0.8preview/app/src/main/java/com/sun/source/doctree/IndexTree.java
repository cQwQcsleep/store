package com.sun.source.doctree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IndexTree extends InlineTagTree {
    List<? extends DocTree> getDescription();

    DocTree getSearchTerm();
}
