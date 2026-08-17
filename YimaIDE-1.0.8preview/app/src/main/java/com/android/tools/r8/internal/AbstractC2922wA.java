package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2922wA implements Iterable {
    public final int b;
    public final int c;
    public final int d;

    public AbstractC2922wA(int i, int i2, int i3) {
        if (i3 == 0) {
            w01.a("Step must be non-zero.");
            throw null;
        }
        if (i3 == Integer.MIN_VALUE) {
            w01.a("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
            throw null;
        }
        this.b = i;
        this.c = VY.a(i, i2, i3);
        this.d = i3;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new C3008xA(this.b, this.c, this.d);
    }
}
