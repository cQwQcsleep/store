package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1032a30 extends AbstractC1456f30 implements HU {
    public C1286d30 g;
    public final /* synthetic */ C1627h30 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1032a30(C1627h30 c1627h30) {
        super(c1627h30);
        this.h = c1627h30;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        C1286d30 c1286d30 = new C1286d30(this.h, b());
        this.g = c1286d30;
        return c1286d30;
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        C1286d30 c1286d30 = new C1286d30(this.h, d());
        this.g = c1286d30;
        return c1286d30;
    }

    @Override // com.android.tools.r8.internal.AbstractC1456f30, java.util.ListIterator, java.util.Iterator
    public final void remove() {
        super.remove();
        this.g.b = -1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
