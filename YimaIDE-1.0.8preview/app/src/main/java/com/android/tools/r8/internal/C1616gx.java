package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1616gx extends AbstractC2043lx implements HU {
    public C1872jx g;
    public final /* synthetic */ C2214nx h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1616gx(C2214nx c2214nx) {
        super(c2214nx);
        this.h = c2214nx;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        C1872jx c1872jx = new C1872jx(this.h, b());
        this.g = c1872jx;
        return c1872jx;
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        C1872jx c1872jx = new C1872jx(this.h, d());
        this.g = c1872jx;
        return c1872jx;
    }

    @Override // com.android.tools.r8.internal.AbstractC2043lx, java.util.ListIterator, java.util.Iterator
    public final void remove() {
        super.remove();
        this.g.b = -1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
