package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1311dN extends AbstractC1228cN {
    public final /* synthetic */ AbstractC1396eN b;

    public C1311dN(AbstractC1396eN abstractC1396eN) {
        this.b = abstractC1396eN;
    }

    @Override // com.android.tools.r8.internal.AbstractC1228cN
    public final Map a() {
        return this.b;
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer consumer) {
        this.b.a(consumer);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return this.b.e();
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Spliterator spliterator() {
        return this.b.i();
    }
}
