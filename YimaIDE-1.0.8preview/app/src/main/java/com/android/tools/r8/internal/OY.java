package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class OY extends AbstractC2205no {
    public static final OY a = new OY();

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final boolean a(Object obj, Object obj2) {
        return ((com.android.tools.r8.graph.B5) obj).e() == ((com.android.tools.r8.graph.B5) obj2).e();
    }

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final int a(Object obj) {
        return ((com.android.tools.r8.graph.B5) obj).getReference().hashCode();
    }
}
