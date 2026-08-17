package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.v1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2818v1 extends AbstractC1198c1 {
    public static final /* synthetic */ boolean e = true;
    public int b;
    public int c = -1;
    public final /* synthetic */ C2904w1 d;

    public C2818v1(C2904w1 c2904w1, int i) {
        this.d = c2904w1;
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.AbstractC1198c1, java.util.ListIterator
    public final void add(Object obj) {
        if (this.c == -1) {
            g33.a();
            return;
        }
        C2904w1 c2904w1 = this.d;
        int i = this.b;
        this.b = i + 1;
        c2904w1.add(i, obj);
        this.c = -1;
        if (e) {
            return;
        }
        this.d.a();
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        int i = this.b;
        C2904w1 c2904w1 = this.d;
        return i < c2904w1.d - c2904w1.c;
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
        C2904w1 c2904w1 = this.d;
        S30 s30 = c2904w1.b;
        int i = c2904w1.c;
        int i2 = this.b;
        this.b = i2 + 1;
        this.c = i2;
        return s30.get(i + i2);
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
        C2904w1 c2904w1 = this.d;
        S30 s30 = c2904w1.b;
        int i = c2904w1.c;
        int i2 = this.b - 1;
        this.b = i2;
        this.c = i2;
        return s30.get(i + i2);
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
        if (e) {
            return;
        }
        this.d.a();
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
