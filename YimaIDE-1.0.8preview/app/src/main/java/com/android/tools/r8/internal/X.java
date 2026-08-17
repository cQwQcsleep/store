package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X extends AbstractC1025a0 {
    public static final /* synthetic */ boolean e = true;
    public int b;
    public int c = -1;
    public final /* synthetic */ Y d;

    public X(Y y, int i) {
        this.d = y;
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.AbstractC1025a0
    public final void a(int i) {
        if (this.c == -1) {
            g33.a();
            return;
        }
        Y y = this.d;
        int i2 = this.b;
        this.b = i2 + 1;
        y.b(i2, i);
        this.c = -1;
        if (e) {
            return;
        }
        this.d.a();
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
        Y y = this.d;
        InterfaceC1981lA interfaceC1981lA = y.b;
        int i = y.c;
        int i2 = this.b - 1;
        this.b = i2;
        this.c = i2;
        return interfaceC1981lA.i(i + i2);
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        int i = this.b;
        Y y = this.d;
        return i < y.d - y.c;
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
        Y y = this.d;
        InterfaceC1981lA interfaceC1981lA = y.b;
        int i = y.c;
        int i2 = this.b;
        this.b = i2 + 1;
        this.c = i2;
        return interfaceC1981lA.i(i + i2);
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
        if (e) {
            return;
        }
        this.d.a();
    }
}
