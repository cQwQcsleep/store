package com.android.tools.r8.experimental.graphinfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class a extends GraphNode {
    public a() {
        super(false);
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public final boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public final int hashCode() {
        return 0;
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public final String toString() {
        return "cycle";
    }
}
