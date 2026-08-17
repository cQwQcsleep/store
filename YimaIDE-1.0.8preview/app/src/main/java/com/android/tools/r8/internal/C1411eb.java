package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1411eb implements Iterator {
    public int b = 0;
    public final /* synthetic */ C1497fb c;

    public C1411eb(C1497fb c1497fb) {
        this.c = c1497fb;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.c.c;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        char[] cArr = this.c.b;
        int i = this.b;
        this.b = i + 1;
        return Character.valueOf(cArr[i]);
    }

    @Override // java.util.Iterator
    public final void remove() {
        C1497fb c1497fb = this.c;
        int i = c1497fb.c;
        c1497fb.c = i - 1;
        int i2 = this.b;
        int i3 = i2 - 1;
        this.b = i3;
        char[] cArr = c1497fb.b;
        System.arraycopy(cArr, i2, cArr, i3, i - i2);
    }
}
