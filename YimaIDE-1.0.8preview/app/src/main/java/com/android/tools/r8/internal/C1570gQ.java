package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1570gQ implements Iterator {
    public final InterfaceC1231cQ b;
    public final Iterator c;
    public AbstractC1314dQ d;
    public int e;
    public int f;
    public boolean g;

    public C1570gQ(InterfaceC1231cQ interfaceC1231cQ, Iterator it) {
        this.b = interfaceC1231cQ;
        this.c = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.e > 0 || this.c.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        if (this.e == 0) {
            AbstractC1314dQ abstractC1314dQ = (AbstractC1314dQ) this.c.next();
            this.d = abstractC1314dQ;
            int iA = abstractC1314dQ.a();
            this.e = iA;
            this.f = iA;
        }
        this.e--;
        this.g = true;
        AbstractC1314dQ abstractC1314dQ2 = this.d;
        Objects.requireNonNull(abstractC1314dQ2);
        return abstractC1314dQ2.b();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.g) {
            k2d.a("no calls to next() since the last call to remove()");
            return;
        }
        if (this.f == 1) {
            this.c.remove();
        } else {
            InterfaceC1231cQ interfaceC1231cQ = this.b;
            AbstractC1314dQ abstractC1314dQ = this.d;
            Objects.requireNonNull(abstractC1314dQ);
            interfaceC1231cQ.remove(abstractC1314dQ.b());
        }
        this.f--;
        this.g = false;
    }
}
