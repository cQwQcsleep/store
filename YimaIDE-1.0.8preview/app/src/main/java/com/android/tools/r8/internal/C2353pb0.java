package com.android.tools.r8.internal;

import java.util.AbstractSet;
import java.util.BitSet;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pb0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2353pb0 extends AbstractSet {
    public final /* synthetic */ BitSet b;
    public final /* synthetic */ C2438qb0 c;

    public C2353pb0(C2438qb0 c2438qb0, BitSet bitSet) {
        this.c = c2438qb0;
        this.b = bitSet;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        Integer num = (Integer) this.c.e.c.get(obj);
        return num != null && this.b.get(num.intValue());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2267ob0(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.c.e.b;
    }
}
