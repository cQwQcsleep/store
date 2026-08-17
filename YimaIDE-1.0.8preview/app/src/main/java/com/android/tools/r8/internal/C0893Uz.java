package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Uz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0893Uz extends AbstractC1025a0 {
    public int b;
    public int c = -1;
    public final /* synthetic */ C0919Vz d;

    public C0893Uz(C0919Vz c0919Vz, int i) {
        this.d = c0919Vz;
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.AbstractC1025a0
    public final void a(int i) {
        C0919Vz c0919Vz = this.d;
        int i2 = this.b;
        this.b = i2 + 1;
        c0919Vz.b(i2, i);
        this.c = -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1025a0
    public final void b(int i) {
        int i2 = this.c;
        if (i2 != -1) {
            this.d.c(i2, i);
        } else {
            g33.a();
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1045aA
    public final int g() {
        if (!hasPrevious()) {
            z0e.a();
            return 0;
        }
        int[] iArr = this.d.b;
        int i = this.b - 1;
        this.b = i;
        this.c = i;
        return iArr[i];
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.b < this.d.c;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.b > 0;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.b;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.b - 1;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1640hA
    public final int q() {
        if (!hasNext()) {
            z0e.a();
            return 0;
        }
        int[] iArr = this.d.b;
        int i = this.b;
        this.b = i + 1;
        this.c = i;
        return iArr[i];
    }

    @Override // com.android.tools.r8.internal.W, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        int i = this.c;
        if (i == -1) {
            g33.a();
            return;
        }
        this.d.h(i);
        int i2 = this.c;
        int i3 = this.b;
        if (i2 < i3) {
            this.b = i3 - 1;
        }
        this.c = -1;
    }
}
