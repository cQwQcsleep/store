package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Gz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0530Gz extends T {
    public final /* synthetic */ C0659Lz c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0530Gz(C0659Lz c0659Lz) {
        super(c0659Lz);
        this.c = c0659Lz;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1045aA iterator() {
        return new C0608Jz(this.c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1640hA iterator() {
        return new C0608Jz(this.c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0608Jz(this.c);
    }
}
