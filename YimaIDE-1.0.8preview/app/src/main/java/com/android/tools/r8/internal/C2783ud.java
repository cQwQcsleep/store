package com.android.tools.r8.internal;

import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ud, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2783ud extends AbstractC3038xc {
    public C2783ud() {
        super(null, null);
    }

    public static void a(com.android.tools.r8.graph.I0 i0) {
        throw new C0613Ke("Classpath type already present: " + i0.e.m0());
    }

    @Override // com.android.tools.r8.internal.AbstractC3038xc
    public final com.android.tools.r8.graph.V c() {
        return com.android.tools.r8.graph.V.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC3038xc
    public final String toString() {
        return "classpath classes: " + super.toString();
    }

    public C2783ud(AbstractC1501fd abstractC1501fd) {
        super(null, abstractC1501fd);
    }

    @Override // com.android.tools.r8.internal.AbstractC3038xc
    public final /* bridge */ /* synthetic */ com.android.tools.r8.graph.E0 a(com.android.tools.r8.graph.E0 e0, com.android.tools.r8.graph.E0 e1) {
        a((com.android.tools.r8.graph.I0) e0);
        throw null;
    }

    @Override // com.android.tools.r8.internal.AbstractC3038xc
    public final Supplier a(com.android.tools.r8.graph.E0 e0) {
        return (com.android.tools.r8.graph.I0) e0;
    }
}
