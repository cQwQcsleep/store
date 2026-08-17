package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ly, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0658Ly extends T {
    public final /* synthetic */ C0944Wy c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0658Ly(C0944Wy c0944Wy) {
        super(c0944Wy);
        this.c = c0944Wy;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1045aA iterator() {
        return new C0633Ky(this.c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1640hA iterator() {
        return new C0633Ky(this.c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0633Ky(this.c);
    }
}
