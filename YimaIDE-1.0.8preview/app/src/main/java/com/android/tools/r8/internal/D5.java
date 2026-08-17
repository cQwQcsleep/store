package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D5 implements Iterator {
    public final J5 b;
    public AbstractC0890Uw c;
    public final /* synthetic */ AbstractC0890Uw d;

    public D5(H5 h5, AbstractC0890Uw abstractC0890Uw) {
        AbstractC0890Uw next;
        this.d = abstractC0890Uw;
        J5 j5H = h5.H();
        this.b = j5H;
        this.c = (!j5H.hasNext() || (next = j5H.next()) == abstractC0890Uw) ? null : next;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        AbstractC0890Uw next;
        AbstractC0890Uw abstractC0890Uw = this.c;
        AbstractC0890Uw abstractC0890Uw2 = null;
        if (abstractC0890Uw == null) {
            z0e.a();
            return null;
        }
        if (this.b.hasNext() && (next = this.b.next()) != this.d) {
            abstractC0890Uw2 = next;
        }
        this.c = abstractC0890Uw2;
        return abstractC0890Uw;
    }
}
