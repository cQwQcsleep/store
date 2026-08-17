package com.android.tools.r8.internal;

import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ya, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3118ya extends K8 {
    public C3118ya(com.android.tools.r8.graph.I2 i2) {
        super(i2, true);
    }

    @Override // com.android.tools.r8.internal.K8
    public final void a(C0602Jt c0602Jt, C0583Ja c0583Ja) {
        c0602Jt.a(c0583Ja.a, getType(), true);
    }

    @Override // com.android.tools.r8.internal.K8, com.android.tools.r8.internal.AbstractC3175z9
    public final void a(com.android.tools.r8.graph.Z5 z5, ListIterator listIterator) {
        z5.e(getType());
    }

    @Override // com.android.tools.r8.internal.K8, com.android.tools.r8.internal.InterfaceC0998Za
    public final AbstractC3175z9 a(com.android.tools.r8.graph.I2 i2) {
        return new C3118ya(i2);
    }
}
