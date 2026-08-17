package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class XS extends Y0 {
    public int b = -1;
    public int c = 0;
    public final /* synthetic */ YS d;

    public XS(YS ys) {
        this.d = ys;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c < this.d.b.d;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        ZS zs = this.d.b;
        Object[] objArr = zs.b;
        int i = this.c;
        this.b = i;
        Object obj = objArr[i];
        boolean[] zArr = zs.c;
        this.c = i + 1;
        return new S0(obj, zArr[i]);
    }

    @Override // com.android.tools.r8.internal.Y0, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        if (this.b == -1) {
            g33.a();
            return;
        }
        this.b = -1;
        ZS zs = this.d.b;
        int i = zs.d;
        zs.d = i - 1;
        int i2 = this.c;
        int i3 = i2 - 1;
        this.c = i3;
        int i4 = i - i2;
        Object[] objArr = zs.b;
        System.arraycopy(objArr, i2, objArr, i3, i4);
        boolean[] zArr = this.d.b.c;
        int i5 = this.c;
        System.arraycopy(zArr, i5 + 1, zArr, i5, i4);
        ZS zs2 = this.d.b;
        zs2.b[zs2.d] = null;
    }
}
