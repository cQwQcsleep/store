package com.android.tools.r8.internal;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class YC extends AbstractC1529fv {
    public final Map f;
    public final AbstractC0551Hu g;
    public final long h;
    public transient C1357dv i;

    public YC(HashMap map, AbstractC0551Hu abstractC0551Hu, long j) {
        this.f = map;
        this.g = abstractC0551Hu;
        this.h = j;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final int b(Object obj) {
        return ((Integer) this.f.getOrDefault(obj, 0)).intValue();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1529fv
    /* JADX INFO: renamed from: g */
    public final AbstractC2554rv F() {
        C1357dv c1357dv = this.i;
        if (c1357dv != null) {
            return c1357dv;
        }
        C1357dv c1357dv2 = new C1357dv(this.g, this);
        this.i = c1357dv2;
        return c1357dv2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1529fv
    public final AbstractC1314dQ j(int i) {
        return (AbstractC1314dQ) this.g.get(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return MB.a(this.h);
    }
}
