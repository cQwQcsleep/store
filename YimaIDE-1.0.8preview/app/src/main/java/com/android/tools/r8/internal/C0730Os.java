package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Os, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0730Os extends AbstractC1482fN {
    public final /* synthetic */ C0756Ps c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0730Os(C0756Ps c0756Ps) {
        super(c0756Ps);
        this.c = c0756Ps;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0704Ns(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        C0860Ts c0860Ts = this.c.b;
        int iA = AbstractC1189bt.a(obj);
        int i = C0860Ts.j;
        C0627Ks c0627KsB = c0860Ts.b(iA, obj);
        if (c0627KsB == null) {
            return false;
        }
        this.c.b.a(c0627KsB);
        return true;
    }
}
