package com.sun.source.tree;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ParameterizedTypeTree extends Tree {
    Tree getType();

    List<? extends Tree> getTypeArguments();
}
