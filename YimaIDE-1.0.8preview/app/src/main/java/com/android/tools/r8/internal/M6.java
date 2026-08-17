package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M6 extends AbstractC1789j {
    public final boolean b;
    public int c;

    public M6(boolean z) {
        this.b = z;
    }

    @Override // com.android.tools.r8.internal.AbstractC1789j
    public final boolean a() {
        if (hasPrevious()) {
            this.c = 0;
            return this.b;
        }
        z0e.a();
        return false;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.c == 0;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.c == 1;
    }

    @Override // com.android.tools.r8.internal.K6
    public final boolean n() {
        if (hasNext()) {
            this.c = 1;
            return this.b;
        }
        z0e.a();
        return false;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.c;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.c - 1;
    }
}
