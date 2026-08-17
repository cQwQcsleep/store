package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class B6 extends AbstractC1705i {
    public int b = 0;
    public final /* synthetic */ C6 c;

    public B6(C6 c6) {
        this.c = c6;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.c.c;
    }

    @Override // com.android.tools.r8.internal.K6
    public final boolean n() {
        if (!hasNext()) {
            z0e.a();
            return false;
        }
        boolean[] zArr = this.c.b;
        int i = this.b;
        this.b = i + 1;
        return zArr[i];
    }

    @Override // com.android.tools.r8.internal.AbstractC1705i, java.util.Iterator
    public final void remove() {
        C6 c6 = this.c;
        int i = c6.c;
        c6.c = i - 1;
        int i2 = this.b;
        int i3 = i2 - 1;
        this.b = i3;
        boolean[] zArr = c6.b;
        System.arraycopy(zArr, i2, zArr, i3, i - i2);
    }
}
