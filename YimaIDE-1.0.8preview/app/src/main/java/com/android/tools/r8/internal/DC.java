package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class DC implements Iterator {
    public Iterator b = LC.b;
    public final /* synthetic */ Iterable c;

    public DC(Iterable iterable) {
        this.c = iterable;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b.hasNext() || this.c.iterator().hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.b.hasNext()) {
            Iterator it = this.c.iterator();
            this.b = it;
            if (!it.hasNext()) {
                z0e.a();
                return null;
            }
        }
        return this.b.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.b.remove();
    }
}
