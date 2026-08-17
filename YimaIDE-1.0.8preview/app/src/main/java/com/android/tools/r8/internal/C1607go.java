package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.go, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1607go extends AbstractC1522fo {
    public static final /* synthetic */ boolean b = true;
    public final com.android.tools.r8.graph.I2 a;

    public C1607go(com.android.tools.r8.graph.I2 i2) {
        if (b || i2 != null) {
            this.a = i2;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1522fo
    public final AbstractC1522fo a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        com.android.tools.r8.graph.I2 i2C = abstractC3148ys.c(abstractC3148ys2, this.a);
        if (b || i2C.M0()) {
            return new C1607go(i2C);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1522fo
    public final boolean equals(Object obj) {
        return (obj instanceof C1607go) && this.a == ((C1607go) obj).a;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC1522fo
    public final com.android.tools.r8.graph.I2 a(int i) {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.AbstractC1522fo
    public final boolean a(C0333y c0333y) {
        return c0333y.R().b(this.a);
    }
}
