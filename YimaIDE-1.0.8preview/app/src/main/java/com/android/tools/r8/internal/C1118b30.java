package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1118b30 extends AbstractC1456f30 implements HU {
    public final /* synthetic */ C1627h30 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1118b30(C1627h30 c1627h30) {
        super(c1627h30);
        this.g = c1627h30;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return this.g.c[b()];
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return this.g.c[d()];
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
