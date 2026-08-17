package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L30 extends AbstractC1198c1 {
    public int b;
    public int c = -1;
    public final /* synthetic */ M30 d;

    public L30(M30 m30, int i) {
        this.d = m30;
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.AbstractC1198c1, java.util.ListIterator
    public final void add(Object obj) {
        M30 m30 = this.d;
        int i = this.b;
        this.b = i + 1;
        m30.add(i, obj);
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
