package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ErroneousTree extends ExpressionTree {
    List<? extends Tree> getErrorTrees();
}
