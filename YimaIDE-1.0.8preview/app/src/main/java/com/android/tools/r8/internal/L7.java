package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L7 implements Iterator {
    public int b = 0;
    public final int c;
    public final /* synthetic */ U7 d;

    public L7(U7 u7) {
        this.d = u7;
        this.c = u7.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.c;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.b;
        if (i < this.c) {
            this.b = i + 1;
            return Byte.valueOf(this.d.k(i));
        }
        z0e.a();
        return null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
