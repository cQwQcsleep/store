package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3008xA implements Iterator {
    public final int b;
    public final int c;
    public boolean d;
    public int e;

    public C3008xA(int i, int i2, int i3) {
        this.b = i3;
        this.c = i2;
        boolean z = i3 <= 0 ? i >= i2 : i <= i2;
        this.d = z;
        this.e = z ? i : i2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.d;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.e;
        if (i != this.c) {
            this.e = this.b + i;
        } else {
            if (!this.d) {
                z0e.a();
                return null;
            }
            this.d = false;
        }
        return Integer.valueOf(i);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
