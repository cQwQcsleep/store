package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Al, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0360Al extends AbstractC2205no {
    public static final C0360Al a = new C0360Al();

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final boolean a(Object obj, Object obj2) {
        return ((com.android.tools.r8.graph.H0) obj).e() == ((com.android.tools.r8.graph.H0) obj2).e();
    }

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final int a(Object obj) {
        return ((com.android.tools.r8.graph.H0) obj).getReference().hashCode();
    }
}
