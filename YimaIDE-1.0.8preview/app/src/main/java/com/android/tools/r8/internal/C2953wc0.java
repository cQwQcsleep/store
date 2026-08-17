package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wc0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2953wc0 extends AbstractC2269oc0 {
    public C2953wc0(C0245l1 c0245l1) {
        super(c0245l1);
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean B() {
        return false;
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean equals(Object obj) {
        return obj != null && C2953wc0.class == obj.getClass() && this.b == ((C2953wc0) obj).b;
    }

    @Override // com.android.tools.r8.internal.B1
    public final int hashCode() {
        return this.b.hashCode();
    }

    @Override // com.android.tools.r8.internal.B1
    public final String toString() {
        return "SingleStatelessFieldValue(" + this.b.m0() + ")";
    }

    @Override // com.android.tools.r8.internal.AbstractC2269oc0, com.android.tools.r8.internal.B1
    public final RU y() {
        return C1097an.a;
    }
}
