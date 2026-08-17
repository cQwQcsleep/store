package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1790j0 extends AbstractC2047m0 {
    public static final /* synthetic */ boolean e = true;
    public int b;
    public int c = -1;
    public final /* synthetic */ k0 d;

    public C1790j0(k0 k0Var, int i) {
        this.d = k0Var;
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.AbstractC2047m0
    public final long a() {
        if (!hasPrevious()) {
            z0e.a();
            return 0L;
        }
        k0 k0Var = this.d;
        InterfaceC1566gM interfaceC1566gM = k0Var.b;
        int i = k0Var.c;
        int i2 = this.b - 1;
        this.b = i2;
        this.c = i2;
        return interfaceC1566gM.a(i + i2);
    }

    @Override // com.android.tools.r8.internal.AbstractC2047m0
    public final void b(long j) {
        int i = this.c;
        if (i != -1) {
            this.d.b(i, j);
        } else {
            g33.a();
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1481fM
    public final long c() {
        if (!hasNext()) {
            z0e.a();
            return 0L;
        }
        k0 k0Var = this.d;
        InterfaceC1566gM interfaceC1566gM = k0Var.b;
        int i = k0Var.c;
        int i2 = this.b;
        this.b = i2 + 1;
        this.c = i2;
        return interfaceC1566gM.a(i + i2);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        int i = this.b;
        k0 k0Var = this.d;
        return i < k0Var.d - k0Var.c;
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

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        int i = this.c;
        if (i == -1) {
            g33.a();
            return;
        }
        this.d.d(i);
        int i2 = this.c;
        int i3 = this.b;
        if (i2 < i3) {
            this.b = i3 - 1;
        }
        this.c = -1;
        if (e) {
            return;
        }
        this.d.b();
    }

    @Override // com.android.tools.r8.internal.AbstractC2047m0
    public final void a(long j) {
        if (this.c != -1) {
            k0 k0Var = this.d;
            int i = this.b;
            this.b = i + 1;
            k0Var.a(i, j);
            this.c = -1;
            if (e) {
                return;
            }
            this.d.b();
            return;
        }
        g33.a();
    }
}
