package com.intellij.util.containers;

import com.intellij.util.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final /* synthetic */ class g implements Function {
    public final /* synthetic */ FilteredTraverserBase.MappedTree b;

    @Override // com.intellij.util.Function
    public final Object fun(Object obj) {
        return this.b.map(obj);
    }
}
