package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I3 implements Iterator {
    public final Object[] b;
    public int c;

    public I3(Object[] objArr) {
        KB.c(objArr, "array");
        this.b = objArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c < this.b.length;
    }

    @Override // java.util.Iterator
    public final Object next() {
        try {
            Object[] objArr = this.b;
            int i = this.c;
            this.c = i + 1;
            return objArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.c--;
            hb9.a(e.getMessage());
            return null;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
