package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UL extends AbstractC1706i0 implements Cloneable, Set {
    public final /* synthetic */ ZL b;

    public UL(ZL zl) {
        this.b = zl;
    }

    @Override // com.android.tools.r8.internal.AbstractC1706i0
    public final InterfaceC1481fM a() {
        return new TL(this.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC1706i0
    public final boolean b(long j) {
        return this.b.a(j);
    }

    @Override // com.android.tools.r8.internal.AbstractC1706i0
    public final boolean c(long j) {
        ZL zl = this.b;
        int i = zl.h;
        zl.c(j);
        return this.b.h != i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (set.size() != size()) {
            return false;
        }
        return containsAll(set);
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        ZL zl = this.b;
        int i = zl.h;
        TL tl = new TL(zl);
        int i2 = 0;
        while (true) {
            int i3 = i - 1;
            if (i == 0) {
                return i2;
            }
            long j = tl.h.b[tl.a()];
            i2 += (int) (j ^ (j >>> 32));
            i = i3;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new TL(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.h;
    }
}
