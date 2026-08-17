package com.android.tools.r8.internal;

import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1449f0 extends C1363e0 implements ListIterator {
    public final /* synthetic */ AbstractC1620h0 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1449f0(AbstractC1620h0 abstractC1620h0, int i) {
        super(abstractC1620h0);
        this.d = abstractC1620h0;
        int iA = abstractC1620h0.a();
        if (i < 0 || i > iA) {
            rnd.a("index: ", i, ", size: ", iA);
            throw null;
        }
        this.b = i;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.b > 0;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.b;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            z0e.a();
            return null;
        }
        AbstractC1620h0 abstractC1620h0 = this.d;
        int i = this.b - 1;
        this.b = i;
        return abstractC1620h0.get(i);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.b - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
