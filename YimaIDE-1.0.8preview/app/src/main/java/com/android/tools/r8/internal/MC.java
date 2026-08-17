package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class MC implements Iterator {
    public final Iterator b;
    public boolean c;
    public Object d;

    public MC(Iterator it) {
        it.getClass();
        this.b = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c || this.b.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.c) {
            return this.b.next();
        }
        Object obj = this.d;
        this.c = false;
        this.d = null;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.c) {
            k2d.a("Can't remove after you've peeked at next");
        } else {
            this.b.remove();
        }
    }
}
