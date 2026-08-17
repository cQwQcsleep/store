package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class W20 extends Y0 {
    public int b = -1;
    public int c = 0;
    public final /* synthetic */ X20 d;

    public W20(X20 x20) {
        this.d = x20;
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
        Y20 y20 = this.d.b;
        Object[] objArr = y20.c;
        int i = this.c;
        this.b = i;
        Object obj = objArr[i];
        int[] iArr = y20.d;
        this.c = i + 1;
        return new C2562s1(iArr[i], obj);
    }

    @Override // com.android.tools.r8.internal.Y0, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        if (this.b == -1) {
            g33.a();
            return;
        }
        this.b = -1;
        Y20 y20 = this.d.b;
        int i = y20.e;
        y20.e = i - 1;
        int i2 = this.c;
        int i3 = i2 - 1;
        this.c = i3;
        int i4 = i - i2;
        Object[] objArr = y20.c;
        System.arraycopy(objArr, i2, objArr, i3, i4);
        int[] iArr = this.d.b.d;
        int i5 = this.c;
        System.arraycopy(iArr, i5 + 1, iArr, i5, i4);
        Y20 y21 = this.d.b;
        y21.c[y21.e] = null;
    }
}
