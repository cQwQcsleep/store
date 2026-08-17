package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface TryTree extends StatementTree {
    BlockTree getBlock();

    List<? extends CatchTree> getCatches();

    BlockTree getFinallyBlock();

    List<? extends Tree> getResources();
}
