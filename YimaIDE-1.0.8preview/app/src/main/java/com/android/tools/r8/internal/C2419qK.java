package com.android.tools.r8.internal;

import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2419qK implements ListIterator {
    public int b;
    public C2334pK c;
    public C2334pK d;
    public C2334pK e;
    public int f;
    public final /* synthetic */ C2590sK g;

    public C2419qK(C2590sK c2590sK, int i) {
        this.g = c2590sK;
        this.f = c2590sK.j;
        int i2 = c2590sK.i;
        DX.b(i, i2);
        if (i < i2 / 2) {
            this.c = c2590sK.f;
            while (true) {
                int i3 = i - 1;
                if (i <= 0) {
                    break;
                }
                if (this.g.j != this.f) {
                    a1e.a();
                    throw null;
                }
                C2334pK c2334pK = this.c;
                if (c2334pK == null) {
                    z0e.a();
                    throw null;
                }
                this.d = c2334pK;
                this.e = c2334pK;
                this.c = c2334pK.d;
                this.b++;
                i = i3;
            }
        } else {
            this.e = c2590sK.g;
            this.b = i2;
            while (true) {
                int i4 = i + 1;
                if (i >= i2) {
                    break;
                }
                if (this.g.j != this.f) {
                    a1e.a();
                    throw null;
                }
                C2334pK c2334pK2 = this.e;
                if (c2334pK2 == null) {
                    z0e.a();
                    throw null;
                }
                this.d = c2334pK2;
                this.c = c2334pK2;
                this.e = c2334pK2.e;
                this.b--;
                i = i4;
            }
        }
        this.d = null;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        if (this.g.j == this.f) {
            return this.c != null;
        }
        a1e.a();
        return false;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        if (this.g.j == this.f) {
            return this.e != null;
        }
        a1e.a();
        return false;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        if (this.g.j != this.f) {
            a1e.a();
            return null;
        }
        C2334pK c2334pK = this.c;
        if (c2334pK == null) {
            z0e.a();
            return null;
        }
        this.d = c2334pK;
        this.e = c2334pK;
        this.c = c2334pK.d;
        this.b++;
        return c2334pK;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.b;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (this.g.j != this.f) {
            a1e.a();
            return null;
        }
        C2334pK c2334pK = this.e;
        if (c2334pK == null) {
            z0e.a();
            return null;
        }
        this.d = c2334pK;
        this.c = c2334pK;
        this.e = c2334pK.e;
        this.b--;
        return c2334pK;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.b - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        C2590sK c2590sK = this.g;
        if (c2590sK.j != this.f) {
            a1e.a();
            return;
        }
        C2334pK c2334pK = this.d;
        if (!(c2334pK != null)) {
            k2d.a("no calls to next() since the last call to remove()");
            return;
        }
        if (c2334pK != this.c) {
            this.e = c2334pK.e;
            this.b--;
        } else {
            this.c = c2334pK.d;
        }
        C2590sK.a(c2590sK, c2334pK);
        this.d = null;
        this.f = this.g.j;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
