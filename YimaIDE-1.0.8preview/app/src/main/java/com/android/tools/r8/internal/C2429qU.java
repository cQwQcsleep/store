package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2429qU extends AbstractC1198c1 {
    public int b;
    public int c = -1;
    public final /* synthetic */ C2514rU d;

    public C2429qU(C2514rU c2514rU, int i) {
        this.d = c2514rU;
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.AbstractC1198c1, java.util.ListIterator
    public final void add(Object obj) {
        C2514rU c2514rU = this.d;
        int i = this.b;
        this.b = i + 1;
        c2514rU.add(i, obj);
        this.c = -1;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.b < this.d.c;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.b > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        Object[] objArr = this.d.b;
        int i = this.b;
        this.b = i + 1;
        this.c = i;
        return objArr[i];
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.b;
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        if (!hasPrevious()) {
            z0e.a();
            return null;
        }
        Object[] objArr = this.d.b;
        int i = this.b - 1;
        this.b = i;
        this.c = i;
        return objArr[i];
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.b - 1;
    }

    @Override // com.android.tools.r8.internal.Y0, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        int i = this.c;
        if (i == -1) {
            g33.a();
            return;
        }
        this.d.remove(i);
        int i2 = this.c;
        int i3 = this.b;
        if (i2 < i3) {
            this.b = i3 - 1;
        }
        this.c = -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1198c1, java.util.ListIterator
    public final void set(Object obj) {
        int i = this.c;
        if (i != -1) {
            this.d.set(i, obj);
        } else {
            g33.a();
        }
    }
}
