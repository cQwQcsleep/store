package com.android.tools.r8.internal;

import defpackage.qc6;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2042lw implements Iterable {
    public int b;
    public G c;
    public G d;
    public G[] e;

    public final void a(G g) {
        this.b++;
        G g2 = this.d;
        if (g2 == null) {
            this.c = g;
            this.d = g;
        } else {
            g2.e = g;
            g.d = g2;
        }
        this.d = g;
        this.e = null;
        g.f = 0;
    }

    public final int b(G g) {
        if (this.e == null) {
            this.e = a();
        }
        return g.f;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new C1956kw(this);
    }

    public final G j(int i) {
        if (i < 0 || i >= this.b) {
            qc6.a();
            return null;
        }
        if (this.e == null) {
            this.e = a();
        }
        return this.e[i];
    }

    public final G[] a() {
        G g = this.c;
        G[] gArr = new G[this.b];
        int i = 0;
        while (g != null) {
            gArr[i] = g;
            g.f = i;
            g = g.e;
            i++;
        }
        return gArr;
    }
}
