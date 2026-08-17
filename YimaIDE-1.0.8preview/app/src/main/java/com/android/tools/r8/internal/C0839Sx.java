package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Sx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0839Sx extends Y0 {
    public int b = -1;
    public int c = 0;
    public final /* synthetic */ C0865Tx d;

    public C0839Sx(C0865Tx c0865Tx) {
        this.d = c0865Tx;
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
        C0891Ux c0891Ux = this.d.b;
        int[] iArr = c0891Ux.c;
        int i = this.c;
        this.b = i;
        int i2 = iArr[i];
        Object[] objArr = c0891Ux.d;
        this.c = i + 1;
        return new L(i2, objArr[i]);
    }

    @Override // com.android.tools.r8.internal.Y0, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        if (this.b == -1) {
            g33.a();
            return;
        }
        this.b = -1;
        C0891Ux c0891Ux = this.d.b;
        int i = c0891Ux.e;
        c0891Ux.e = i - 1;
        int i2 = this.c;
        int i3 = i2 - 1;
        this.c = i3;
        int i4 = i - i2;
        int[] iArr = c0891Ux.c;
        System.arraycopy(iArr, i2, iArr, i3, i4);
        Object[] objArr = this.d.b.d;
        int i5 = this.c;
        System.arraycopy(objArr, i5 + 1, objArr, i5, i4);
        C0891Ux c0891Ux2 = this.d.b;
        c0891Ux2.d[c0891Ux2.e] = null;
    }
}
