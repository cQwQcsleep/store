package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0 implements Iterator {
    public Map.Entry b;
    public final /* synthetic */ Iterator c;
    public final /* synthetic */ E0 d;

    public C0(E0 e0, Iterator it) {
        this.d = e0;
        this.c = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry entry = (Map.Entry) this.c.next();
        this.b = entry;
        return new B0(this, entry);
    }

    @Override // java.util.Iterator
    public final void remove() {
        Map.Entry entry = this.b;
        if (!(entry != null)) {
            k2d.a("no calls to next() since the last call to remove()");
            return;
        }
        E0 e0 = this.d;
        C1254ch c1254ch = (C1254ch) entry.getValue();
        int i = c1254ch.b;
        c1254ch.b = 0;
        e0.e -= (long) i;
        this.c.remove();
        this.b = null;
    }
}
