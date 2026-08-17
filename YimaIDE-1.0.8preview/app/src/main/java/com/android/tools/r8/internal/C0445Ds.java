package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ds, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0445Ds extends AbstractC3220zi0 {
    public AbstractC3220zi0 a = null;

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) {
        AbstractC3220zi0 abstractC3220zi0 = this.a;
        if (abstractC3220zi0 != null) {
            abstractC3220zi0.a(c2754uD, obj);
        } else {
            k2d.a("Adapter for type with cyclic dependency has been used before dependency has been resolved");
        }
    }
}
