package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1193bx extends Y0 {
    public int b = -1;
    public int c = 0;
    public final /* synthetic */ C1275cx d;

    public C1193bx(C1275cx c1275cx) {
        this.d = c1275cx;
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
        C1359dx c1359dx = this.d.b;
        int[] iArr = c1359dx.c;
        int i = this.c;
        this.b = i;
        int i2 = iArr[i];
        int[] iArr2 = c1359dx.d;
        this.c = i + 1;
        return new I(i2, iArr2[i]);
    }

    @Override // com.android.tools.r8.internal.Y0, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        if (this.b == -1) {
            g33.a();
            return;
        }
        this.b = -1;
        C1359dx c1359dx = this.d.b;
        int i = c1359dx.e;
        c1359dx.e = i - 1;
        int i2 = this.c;
        int i3 = i2 - 1;
        this.c = i3;
        int i4 = i - i2;
        int[] iArr = c1359dx.c;
        System.arraycopy(iArr, i2, iArr, i3, i4);
        int[] iArr2 = this.d.b.d;
        int i5 = this.c;
        System.arraycopy(iArr2, i5 + 1, iArr2, i5, i4);
    }
}
