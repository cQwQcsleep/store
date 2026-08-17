package com.android.tools.r8.graph;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class F5 implements Iterable {
    public final Map b;

    public F5(TreeMap treeMap) {
        this.b = treeMap;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.b.values().iterator();
    }
}
