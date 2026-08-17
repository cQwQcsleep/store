package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.g30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1542g30 extends AbstractC1456f30 implements InterfaceC2067mA {
    public final /* synthetic */ C1627h30 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1542g30(C1627h30 c1627h30) {
        super(c1627h30);
        this.g = c1627h30;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1045aA
    public final int g() {
        return this.g.d[d()];
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return Integer.valueOf(this.g.d[b()]);
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return Integer.valueOf(this.g.d[d()]);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1640hA
    public final int q() {
        return this.g.d[b()];
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
