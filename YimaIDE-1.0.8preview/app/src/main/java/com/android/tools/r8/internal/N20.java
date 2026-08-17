package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N20 extends AbstractC1619h {
    public final /* synthetic */ V20 b;

    public N20(V20 v20) {
        this.b = v20;
    }

    @Override // com.android.tools.r8.internal.AbstractC1619h
    public final boolean b(boolean z) {
        V20 v20 = this.b;
        boolean[] zArr = v20.c;
        Object[] objArr = v20.b;
        if (v20.e && zArr[v20.f] == z) {
            return true;
        }
        int i = v20.f;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return false;
            }
            if (objArr[i2] != null && zArr[i2] == z) {
                return true;
            }
            i = i2;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.F6, java.util.Set
    public final K6 iterator() {
        return new U20(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.b.h;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new U20(this.b);
    }
}
