package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N30 extends Y0 {
    public int b = 0;
    public final /* synthetic */ O30 c;

    public N30(O30 o30) {
        this.c = o30;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.c.c;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        Object[] objArr = this.c.b;
        int i = this.b;
        this.b = i + 1;
        return objArr[i];
    }

    @Override // com.android.tools.r8.internal.Y0, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        O30 o30 = this.c;
        int i = o30.c;
        o30.c = i - 1;
        int i2 = this.b;
        int i3 = i2 - 1;
        this.b = i3;
        Object[] objArr = o30.b;
        System.arraycopy(objArr, i2, objArr, i3, i - i2);
        O30 o31 = this.c;
        o31.b[o31.c] = null;
    }
}
