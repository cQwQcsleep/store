package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class E7 implements Iterator {
    public final int b;
    public final byte[] c;
    public int d = 0;

    public E7(byte[] bArr) {
        this.b = bArr.length;
        this.c = bArr;
    }

    public final byte a() {
        byte[] bArr = this.c;
        int i = this.d;
        this.d = i + 1;
        return bArr[i];
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.d < this.b;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return Byte.valueOf(a());
    }
}
