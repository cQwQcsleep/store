package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aM, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1057aM extends AbstractC2047m0 {
    public int b;
    public int c = -1;
    public final /* synthetic */ C1143bM d;

    public C1057aM(C1143bM c1143bM, int i) {
        this.d = c1143bM;
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.AbstractC2047m0
    public final long a() {
        if (!hasPrevious()) {
            z0e.a();
            return 0L;
        }
        long[] jArr = this.d.b;
        int i = this.b - 1;
        this.b = i;
        this.c = i;
        return jArr[i];
    }

    @Override // com.android.tools.r8.internal.AbstractC2047m0
    public final void b(long j) {
        int i = this.c;
        if (i != -1) {
            this.d.b(i, j);
        } else {
            g33.a();
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1481fM
    public final long c() {
        if (!hasNext()) {
            z0e.a();
            return 0L;
        }
        long[] jArr = this.d.b;
        int i = this.b;
        this.b = i + 1;
        this.c = i;
        return jArr[i];
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.d.c;
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
    public final int previousIndex() {
        return this.b - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        int i = this.c;
        if (i == -1) {
            g33.a();
            return;
        }
        this.d.d(i);
        int i2 = this.c;
        int i3 = this.b;
        if (i2 < i3) {
            this.b = i3 - 1;
        }
        this.c = -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC2047m0
    public final void a(long j) {
        C1143bM c1143bM = this.d;
        int i = this.b;
        this.b = i + 1;
        c1143bM.a(i, j);
        this.c = -1;
    }
}
