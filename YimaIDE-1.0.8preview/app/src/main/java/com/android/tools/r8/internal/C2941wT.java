package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2941wT extends AbstractC1706i0 {
    public final /* synthetic */ ET b;

    public C2941wT(ET et) {
        this.b = et;
    }

    @Override // com.android.tools.r8.internal.AbstractC1706i0
    public final InterfaceC1481fM a() {
        return new DT(this.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC1706i0
    public final boolean b(long j) {
        return this.b.a(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new DT(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.b.h;
    }
}
