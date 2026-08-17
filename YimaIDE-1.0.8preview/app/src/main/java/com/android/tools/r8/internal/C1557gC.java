package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gC, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1557gC implements Comparable {
    public final com.android.tools.r8.graph.B5 b;
    public final com.android.tools.r8.graph.B5 c;
    public final com.android.tools.r8.graph.G d;

    public C1557gC(com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B5 b6, com.android.tools.r8.graph.G g) {
        this.b = b5;
        this.c = b6;
        this.d = g;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.b.getReference().compareTo(((C1557gC) obj).b.getReference());
    }
}
