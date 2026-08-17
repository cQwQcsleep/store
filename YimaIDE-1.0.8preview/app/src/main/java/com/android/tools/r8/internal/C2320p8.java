package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p8, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2320p8 implements Iterator {
    public int b = 0;
    public final /* synthetic */ C2490r8 c;

    public C2320p8(C2490r8 c2490r8) {
        this.c = c2490r8;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.c.size();
    }

    @Override // java.util.Iterator
    public final Object next() {
        com.android.tools.r8.graph.I2 i2 = (com.android.tools.r8.graph.I2) this.c.b.get(this.b);
        Object obj = this.c.c.get(this.b);
        this.b++;
        return new C2406q8(i2, obj);
    }
}
