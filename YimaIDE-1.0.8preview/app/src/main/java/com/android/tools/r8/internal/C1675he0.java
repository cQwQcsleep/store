package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.he0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1675he0 extends B1 {
    public static final /* synthetic */ boolean b = true;
    public final RU a;

    public C1675he0(RU ru) {
        if (b || !ru.e()) {
            this.a = ru;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean A() {
        return this.a.d();
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean B() {
        return true;
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean F() {
        return true;
    }

    @Override // com.android.tools.r8.internal.B1
    public final B1 b(C0333y c0333y, com.android.tools.r8.graph.I2 i2, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        RU ruA = this.a.a(c0333y, abstractC3148ys, abstractC3148ys2);
        return ruA.e() ? Ak0.a : new C1675he0(ruA);
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || C1675he0.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((C1675he0) obj).a);
    }

    @Override // com.android.tools.r8.internal.B1
    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.internal.B1
    public final String toString() {
        return "StatefulValue";
    }

    @Override // com.android.tools.r8.internal.B1
    public final C1675he0 u() {
        return this;
    }

    @Override // com.android.tools.r8.internal.B1
    public final int x() {
        return this.a.c();
    }

    @Override // com.android.tools.r8.internal.B1
    public final RU y() {
        return this.a;
    }
}
