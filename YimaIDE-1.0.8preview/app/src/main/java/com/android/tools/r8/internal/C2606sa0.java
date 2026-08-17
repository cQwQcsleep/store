package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sa0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2606sa0 implements Iterator {
    public final C2521ra0 b;
    public BL c;
    public int d;

    public C2606sa0(C2692ta0 c2692ta0) {
        C2521ra0 c2521ra0 = new C2521ra0(c2692ta0);
        this.b = c2521ra0;
        this.c = new BL(c2521ra0.next());
        this.d = c2692ta0.d;
    }

    public final byte a() {
        if (!this.c.hasNext()) {
            this.c = new BL(this.b.next());
        }
        this.d--;
        return this.c.a();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.d > 0;
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
