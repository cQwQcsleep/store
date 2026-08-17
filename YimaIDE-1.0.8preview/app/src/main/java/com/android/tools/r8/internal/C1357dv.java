package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1357dv extends AbstractC2212nv {
    public final List e;
    public final InterfaceC1231cQ f;

    public C1357dv(List list, InterfaceC1231cQ interfaceC1231cQ) {
        this.e = list;
        this.f = interfaceC1231cQ;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f.contains(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2212nv
    public final Object get(int i) {
        return ((AbstractC1314dQ) this.e.get(i)).b();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.e.size();
    }
}
