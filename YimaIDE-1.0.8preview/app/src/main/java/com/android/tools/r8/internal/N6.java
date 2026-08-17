package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N6 extends AbstractC1705i {
    public final K6 b;

    public N6(K6 k6) {
        this.b = k6;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b.hasNext();
    }

    @Override // com.android.tools.r8.internal.K6
    public final boolean n() {
        return this.b.n();
    }
}
