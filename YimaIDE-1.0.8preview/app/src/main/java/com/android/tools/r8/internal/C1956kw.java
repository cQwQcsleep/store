package com.android.tools.r8.internal;

import defpackage.qc6;
import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1956kw implements ListIterator {
    public G b;
    public G c;
    public G d;
    public final /* synthetic */ C2042lw e;

    public C1956kw(C2042lw c2042lw) {
        this.e = c2042lw;
        int i = c2042lw.b;
        if (i < 0) {
            qc6.a();
            throw null;
        }
        if (i == 0) {
            this.b = null;
            this.c = c2042lw.d;
        } else {
            G g = c2042lw.c;
            this.b = g;
            this.c = g.d;
        }
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        G g = this.b;
        if (g != null) {
            C2042lw c2042lw = this.e;
            G g2 = (G) obj;
            c2042lw.b++;
            G g3 = g.d;
            if (g3 == null) {
                c2042lw.c = g2;
            } else {
                g3.e = g2;
            }
            g.d = g2;
            g2.e = g;
            g2.d = g3;
            c2042lw.e = null;
            g2.f = 0;
        } else {
            G g4 = this.c;
            C2042lw c2042lw2 = this.e;
            if (g4 != null) {
                G g5 = (G) obj;
                c2042lw2.b++;
                G g6 = g4.e;
                if (g6 == null) {
                    c2042lw2.d = g5;
                } else {
                    g6.d = g5;
                }
                g4.e = g5;
                g5.e = g6;
                g5.d = g4;
                c2042lw2.e = null;
                g5.f = 0;
            } else {
                c2042lw2.a((G) obj);
            }
        }
        this.c = (G) obj;
        this.d = null;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.b != null;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.c != null;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        G g = this.b;
        if (g == null) {
            z0e.a();
            return null;
        }
        this.c = g;
        this.b = g.e;
        this.d = g;
        return g;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        G g = this.b;
        C2042lw c2042lw = this.e;
        if (g == null) {
            return c2042lw.b;
        }
        if (c2042lw.e == null) {
            c2042lw.e = c2042lw.a();
        }
        return this.b.f;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        G g = this.c;
        if (g == null) {
            z0e.a();
            return null;
        }
        this.b = g;
        this.c = g.d;
        this.d = g;
        return g;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        if (this.c == null) {
            return -1;
        }
        C2042lw c2042lw = this.e;
        if (c2042lw.e == null) {
            c2042lw.e = c2042lw.a();
        }
        return this.c.f;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        G g = this.d;
        if (g == null) {
            g33.a();
            return;
        }
        G g2 = this.b;
        if (g == g2) {
            this.b = g2.e;
        } else {
            this.c = this.c.d;
        }
        C2042lw c2042lw = this.e;
        c2042lw.b--;
        G g3 = g.e;
        G g4 = g.d;
        if (g3 == null) {
            if (g4 == null) {
                c2042lw.c = null;
                c2042lw.d = null;
            } else {
                g4.e = null;
                c2042lw.d = g4;
            }
        } else if (g4 == null) {
            c2042lw.c = g3;
            g3.d = null;
        } else {
            g4.e = g3;
            g3.d = g4;
        }
        c2042lw.e = null;
        g.f = -1;
        g.d = null;
        g.e = null;
        this.d = null;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        G g = this.d;
        if (g == null) {
            g33.a();
            return;
        }
        C2042lw c2042lw = this.e;
        G g2 = (G) obj;
        c2042lw.getClass();
        G g3 = g.e;
        g2.e = g3;
        if (g3 != null) {
            g3.d = g2;
        } else {
            c2042lw.d = g2;
        }
        G g4 = g.d;
        g2.d = g4;
        if (g4 != null) {
            g4.e = g2;
        } else {
            c2042lw.c = g2;
        }
        G[] gArr = c2042lw.e;
        if (gArr != null) {
            int i = g.f;
            gArr[i] = g2;
            g2.f = i;
        } else {
            g2.f = 0;
        }
        g.f = -1;
        g.d = null;
        g.e = null;
        if (this.d == this.c) {
            this.c = g2;
        } else {
            this.b = g2;
        }
    }
}
