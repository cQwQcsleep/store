package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1363e0 implements Iterator {
    public int b;
    public final /* synthetic */ AbstractC1620h0 c;

    public C1363e0(AbstractC1620h0 abstractC1620h0) {
        this.c = abstractC1620h0;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.c.a();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        AbstractC1620h0 abstractC1620h0 = this.c;
        int i = this.b;
        this.b = i + 1;
        return abstractC1620h0.get(i);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
