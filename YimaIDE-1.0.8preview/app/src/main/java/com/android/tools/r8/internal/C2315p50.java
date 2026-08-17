package com.android.tools.r8.internal;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p50, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2315p50 extends AbstractList implements List {
    public final C2401q50 b;

    public C2315p50(C2401q50 c2401q50) {
        this.b = c2401q50;
    }

    public final void a() {
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        C2401q50 c2401q50 = this.b;
        if (c2401q50.d == null) {
            c2401q50.d = new ArrayList(c2401q50.b.size());
            for (int i2 = 0; i2 < c2401q50.b.size(); i2++) {
                c2401q50.d.add(null);
            }
        }
        C2183nc0 c2183nc0 = (C2183nc0) c2401q50.d.get(i);
        if (c2183nc0 == null) {
            C2183nc0 c2183nc1 = new C2183nc0((J0) c2401q50.b.get(i), c2401q50, c2401q50.e);
            c2401q50.d.set(i, c2183nc1);
            c2183nc0 = c2183nc1;
        }
        if (c2183nc0.b == null) {
            H0 h0A = c2183nc0.c.a(c2183nc0);
            c2183nc0.b = h0A;
            h0A.a(c2183nc0.c);
            c2183nc0.b.j();
        }
        return c2183nc0.b;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.b.b.size();
    }
}
