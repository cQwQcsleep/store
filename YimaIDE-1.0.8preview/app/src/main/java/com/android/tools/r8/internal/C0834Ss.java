package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ss, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0834Ss extends AbstractC1482fN {
    public final /* synthetic */ C0860Ts c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0834Ss(C0860Ts c0860Ts) {
        super(c0860Ts);
        this.c = c0860Ts;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0808Rs(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        C0627Ks c0627KsA = this.c.a(AbstractC1189bt.a(obj), obj);
        if (c0627KsA == null) {
            return false;
        }
        this.c.a(c0627KsA);
        c0627KsA.i = null;
        c0627KsA.h = null;
        return true;
    }
}
