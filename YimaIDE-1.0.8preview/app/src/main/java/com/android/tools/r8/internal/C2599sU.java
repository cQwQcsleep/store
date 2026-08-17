package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2599sU extends Y0 {
    public int b = 0;
    public final /* synthetic */ C2685tU c;

    public C2599sU(C2685tU c2685tU) {
        this.c = c2685tU;
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
        C2685tU c2685tU = this.c;
        int i = c2685tU.c;
        c2685tU.c = i - 1;
        int i2 = this.b;
        int i3 = i2 - 1;
        this.b = i3;
        Object[] objArr = c2685tU.b;
        System.arraycopy(objArr, i2, objArr, i3, i - i2);
        C2685tU c2685tU2 = this.c;
        c2685tU2.b[c2685tU2.c] = null;
    }
}
