package com.android.tools.r8.internal;

import java.util.AbstractMap;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RC extends AbstractMap {
    public final RC b;
    public final BitSet c;
    public final HashMap d;
    public final XI e;
    public final /* synthetic */ SC f;

    public RC(SC sc, RC rc, BitSet bitSet) {
        this.f = sc;
        for (RC rc2 = rc; rc2 != null; rc2 = rc2.b) {
            if (rc2.c == bitSet) {
                aca.a("Recursive invocation of ", bitSet);
                throw null;
            }
        }
        this.b = rc;
        this.c = bitSet;
        this.e = rc == null ? null : new XI();
        this.d = new HashMap();
        int i = 0;
        XI xi = null;
        while (true) {
            C2042lw c2042lw = sc.q;
            if (i >= c2042lw.b) {
                return;
            }
            G gJ = c2042lw.j(i);
            if (gJ.a() == 8) {
                XI xi2 = (XI) gJ;
                xi = xi == null ? new XI() : xi;
                this.d.put(xi2, xi);
            } else if (a(i) == this) {
                xi = null;
            }
            i++;
        }
    }

    public final RC a(int i) {
        if (!this.c.get(i)) {
            return null;
        }
        if (!this.f.A.get(i)) {
            return this;
        }
        for (RC rc = this.b; rc != null; rc = rc.b) {
            if (rc.c.get(i)) {
                this = rc;
            }
        }
        return this;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        XI xi = (XI) obj;
        return (XI) a(this.f.q.b(xi)).d.get(xi);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        throw new UnsupportedOperationException();
    }

    public final XI a(XI xi) {
        return (XI) a(this.f.q.b(xi)).d.get(xi);
    }
}
