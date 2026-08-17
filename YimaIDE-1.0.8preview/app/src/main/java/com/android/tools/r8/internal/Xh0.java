package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Xh0 implements Iterator {
    public final Iterator b;
    public final /* synthetic */ Yh0 c;

    public Xh0(Yh0 yh0) {
        this.c = yh0;
        this.b = yh0.a.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.c.b.b(this.b.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
