package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class HC implements Iterator {
    public int b;
    public final /* synthetic */ int c;
    public final /* synthetic */ Iterator d;

    public HC(int i, Iterator it) {
        this.c = i;
        this.d = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.c && this.d.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            this.b++;
            return this.d.next();
        }
        z0e.a();
        return null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.d.remove();
    }
}
