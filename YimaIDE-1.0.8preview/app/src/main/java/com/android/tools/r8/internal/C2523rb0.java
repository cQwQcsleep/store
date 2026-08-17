package com.android.tools.r8.internal;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rb0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2523rb0 extends AbstractSet {
    public final /* synthetic */ int b;
    public final /* synthetic */ AbstractC0706Nu c;

    public C2523rb0(int i, AbstractC0706Nu abstractC0706Nu) {
        this.b = i;
        this.c = abstractC0706Nu;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (set.size() == this.b && this.c.keySet().containsAll(set)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2438qb0(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        int size = this.c.size();
        int i = this.b;
        int i2 = 0;
        if (size < 0) {
            w01.a(AbstractC1784iv.a(size, "n (", ") must be >= 0"));
            return 0;
        }
        if (i < 0) {
            w01.a(AbstractC1784iv.a(i, "k (", ") must be >= 0"));
            return 0;
        }
        if (!(i <= size)) {
            w01.a(Xf0.a("k (%s) > n (%s)", new Object[]{Integer.valueOf(i), Integer.valueOf(size)}));
            return 0;
        }
        if (i > (size >> 1)) {
            i = size - i;
        }
        int[] iArr = AbstractC2410qA.a;
        if (i >= 17 || size > iArr[i]) {
            return Integer.MAX_VALUE;
        }
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return size;
        }
        long j = 1;
        while (i2 < i) {
            long j2 = j * ((long) (size - i2));
            i2++;
            j = j2 / ((long) i2);
        }
        return (int) j;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        StringBuilder sb = new StringBuilder("Sets.combinations(");
        sb.append(this.c.keySet());
        sb.append(", ");
        return AbstractC2181nb0.a(this.b, ")", sb);
    }
}
