package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0970Xy extends Y0 {
    public int b = -1;
    public int c = 0;
    public final /* synthetic */ C0996Yy d;

    public C0970Xy(C0996Yy c0996Yy) {
        this.d = c0996Yy;
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
        C1022Zy c1022Zy = this.d.b;
        int[] iArr = c1022Zy.c;
        int i = this.c;
        this.b = i;
        int i2 = iArr[i];
        Object[] objArr = c1022Zy.d;
        this.c = i + 1;
        return new Q(i2, objArr[i]);
    }

    @Override // com.android.tools.r8.internal.Y0, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        if (this.b == -1) {
            g33.a();
            return;
        }
        this.b = -1;
        C1022Zy c1022Zy = this.d.b;
        int i = c1022Zy.e;
        c1022Zy.e = i - 1;
        int i2 = this.c;
        int i3 = i2 - 1;
        this.c = i3;
        int i4 = i - i2;
        int[] iArr = c1022Zy.c;
        System.arraycopy(iArr, i2, iArr, i3, i4);
        Object[] objArr = this.d.b.d;
        int i5 = this.c;
        System.arraycopy(objArr, i5 + 1, objArr, i5, i4);
        C1022Zy c1022Zy2 = this.d.b;
        c1022Zy2.d[c1022Zy2.e] = null;
    }
}
