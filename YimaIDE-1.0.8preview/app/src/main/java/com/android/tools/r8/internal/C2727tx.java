package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2727tx extends AbstractC1111b0 {
    public final /* synthetic */ C3153yx b;

    public C2727tx(C3153yx c3153yx) {
        this.b = c3153yx;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean f(int i) {
        return this.b.a(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1640hA iterator() {
        return new C2641sx(this.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC1111b0
    public final boolean k(int i) {
        C3153yx c3153yx = this.b;
        int i2 = c3153yx.i;
        c3153yx.remove(i);
        return this.b.i != i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2641sx(this.b);
    }
}
