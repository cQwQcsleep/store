package com.android.tools.r8.experimental.graphinfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class GraphNode {
    private static final a b = new a();
    private final boolean a;

    public GraphNode(boolean z) {
        this.a = z;
    }

    public static GraphNode cycle() {
        return b;
    }

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public final boolean isCycle() {
        return this == cycle();
    }

    public boolean isLibraryNode() {
        return this.a;
    }

    public abstract String toString();
}
