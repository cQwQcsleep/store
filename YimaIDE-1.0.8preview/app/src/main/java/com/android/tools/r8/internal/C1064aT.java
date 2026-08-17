package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1064aT extends Y0 {
    public int b = -1;
    public int c = 0;
    public final /* synthetic */ C1150bT d;

    public C1064aT(C1150bT c1150bT) {
        this.d = c1150bT;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c < this.d.b.e;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        C1234cT c1234cT = this.d.b;
        Object[] objArr = c1234cT.c;
        int i = this.c;
        this.b = i;
        Object obj = objArr[i];
        int[] iArr = c1234cT.d;
        this.c = i + 1;
        return new T0(iArr[i], obj);
    }

    @Override // com.android.tools.r8.internal.Y0, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        if (this.b == -1) {
            g33.a();
            return;
        }
        this.b = -1;
        C1234cT c1234cT = this.d.b;
        int i = c1234cT.e;
        c1234cT.e = i - 1;
        int i2 = this.c;
        int i3 = i2 - 1;
        this.c = i3;
        int i4 = i - i2;
        Object[] objArr = c1234cT.c;
        System.arraycopy(objArr, i2, objArr, i3, i4);
        int[] iArr = this.d.b.d;
        int i5 = this.c;
        System.arraycopy(iArr, i5 + 1, iArr, i5, i4);
        C1234cT c1234cT2 = this.d.b;
        c1234cT2.c[c1234cT2.e] = null;
    }
}
