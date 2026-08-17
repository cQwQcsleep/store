package com.android.tools.r8.graph;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2 implements Iterator {
    public D2 b;
    public final Iterator c;
    public final /* synthetic */ InterfaceC0189d1 d;

    public C2(Iterable iterable, InterfaceC0189d1 interfaceC0189d1) {
        D2 d2X;
        this.d = interfaceC0189d1;
        this.c = iterable.iterator();
        while (this.c.hasNext()) {
            E0 e0A = this.d.a((I2) this.c.next());
            if (e0A != null && e0A.a0()) {
                d2X = e0A.X();
                this.b = d2X;
            }
        }
        d2X = null;
        this.b = d2X;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        D2 d2X;
        D2 d2 = this.b;
        while (this.c.hasNext()) {
            E0 e0A = this.d.a((I2) this.c.next());
            if (e0A != null && e0A.a0()) {
                d2X = e0A.X();
                this.b = d2X;
                return d2;
            }
        }
        d2X = null;
        this.b = d2X;
        return d2;
    }
}
