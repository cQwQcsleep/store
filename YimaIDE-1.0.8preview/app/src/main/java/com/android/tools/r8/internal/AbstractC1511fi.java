package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1511fi extends XR {
    public AbstractC1511fi(C0333y c0333y) {
        super(c0333y, c0333y.A());
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public boolean a(AbstractC3148ys abstractC3148ys) {
        if (this == abstractC3148ys) {
            return true;
        }
        return this.d.a(abstractC3148ys);
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public Iterable b(com.android.tools.r8.graph.I2 i2) {
        return this.d.b(i2);
    }

    @Override // com.android.tools.r8.internal.XR
    public C0245l1 c(C0245l1 c0245l1) {
        return c0245l1;
    }

    @Override // com.android.tools.r8.internal.XR
    public com.android.tools.r8.graph.I2 d(com.android.tools.r8.graph.I2 i2) {
        return i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public com.android.tools.r8.graph.proto.j e(AbstractC3148ys abstractC3148ys, C0322w2 c0322w2) {
        if (this == abstractC3148ys) {
            return com.android.tools.r8.graph.proto.j.d;
        }
        return this.d.e((AbstractC3148ys) null, f(c0322w2));
    }

    @Override // com.android.tools.r8.internal.XR
    public com.android.tools.r8.graph.I2 f(com.android.tools.r8.graph.I2 i2) {
        return i2;
    }

    @Override // com.android.tools.r8.internal.XR
    public C0322w2 f(C0322w2 c0322w2) {
        return c0322w2;
    }

    @Override // com.android.tools.r8.internal.XR
    public C2035lp b(C2035lp c2035lp) {
        return c2035lp;
    }

    public AbstractC1511fi(C0333y c0333y, AbstractC3148ys abstractC3148ys) {
        super(c0333y, abstractC3148ys);
    }

    @Override // com.android.tools.r8.internal.XR
    public C0245l1 b(C0245l1 c0245l1) {
        return c0245l1;
    }

    @Override // com.android.tools.r8.internal.XR
    public C2850vO a(C2850vO c2850vO, C0322w2 c0322w2, AbstractC3148ys abstractC3148ys) {
        return c2850vO.a(this);
    }

    @Override // com.android.tools.r8.internal.XR
    public C0322w2 e(C0322w2 c0322w2) {
        return c0322w2;
    }
}
