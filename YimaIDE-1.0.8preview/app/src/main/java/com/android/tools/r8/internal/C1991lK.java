package com.android.tools.r8.internal;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1991lK extends AbstractSequentialList {
    public final /* synthetic */ C2590sK b;

    public C1991lK(C2590sK c2590sK) {
        this.b = c2590sK;
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer consumer) {
        consumer.getClass();
        for (C2334pK c2334pK = this.b.f; c2334pK != null; c2334pK = c2334pK.d) {
            consumer.accept(c2334pK);
        }
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new C2419qK(this.b, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.b.i;
    }
}
