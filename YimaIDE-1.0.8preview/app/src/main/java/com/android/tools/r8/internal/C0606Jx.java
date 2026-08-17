package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Jx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0606Jx extends N {
    public final /* synthetic */ C0735Ox c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0606Jx(C0735Ox c0735Ox) {
        super(c0735Ox);
        this.c = c0735Ox;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1045aA iterator() {
        return new C0683Mx(this.c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1640hA iterator() {
        return new C0683Mx(this.c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0683Mx(this.c);
    }
}
