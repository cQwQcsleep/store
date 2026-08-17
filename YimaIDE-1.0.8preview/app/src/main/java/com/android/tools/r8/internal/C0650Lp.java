package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0650Lp implements B5 {
    public final C0245l1 a;

    public C0650Lp(C0245l1 c0245l1) {
        this.a = c0245l1;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0370Av
    public final boolean a(C0245l1 c0245l1) {
        return this.a.a(c0245l1);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0370Av
    public final boolean e() {
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0650Lp.class != obj.getClass()) {
            return false;
        }
        return this.a.a(((C0650Lp) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0370Av
    public final C0650Lp j() {
        return this;
    }
}
