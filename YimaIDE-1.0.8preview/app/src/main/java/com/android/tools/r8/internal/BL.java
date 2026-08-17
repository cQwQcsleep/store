package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class BL implements Iterator {
    public int b = 0;
    public final int c;
    public final /* synthetic */ CL d;

    public BL(CL cl) {
        this.d = cl;
        this.c = cl.d.length;
    }

    public final byte a() {
        try {
            byte[] bArr = this.d.d;
            int i = this.b;
            this.b = i + 1;
            return bArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            hb9.a(e.getMessage());
            return (byte) 0;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.c;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return Byte.valueOf(a());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
