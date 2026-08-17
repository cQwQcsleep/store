package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0971Xz extends W {
    public int b = 0;
    public final /* synthetic */ C0997Yz c;

    public C0971Xz(C0997Yz c0997Yz) {
        this.c = c0997Yz;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.c.c;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1640hA
    public final int q() {
        if (!hasNext()) {
            z0e.a();
            return 0;
        }
        int[] iArr = this.c.b;
        int i = this.b;
        this.b = i + 1;
        return iArr[i];
    }

    @Override // com.android.tools.r8.internal.W, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        C0997Yz c0997Yz = this.c;
        int i = c0997Yz.c;
        c0997Yz.c = i - 1;
        int i2 = this.b;
        int i3 = i2 - 1;
        this.b = i3;
        int[] iArr = c0997Yz.b;
        System.arraycopy(iArr, i2, iArr, i3, i - i2);
    }
}
