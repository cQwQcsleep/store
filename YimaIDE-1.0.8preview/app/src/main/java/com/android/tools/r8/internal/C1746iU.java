package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1746iU extends AbstractC2172nU implements HU {
    public C2001lU g;
    public final /* synthetic */ C2344pU h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1746iU(C2344pU c2344pU) {
        super(c2344pU);
        this.h = c2344pU;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        C2001lU c2001lU = new C2001lU(this.h, b());
        this.g = c2001lU;
        return c2001lU;
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        C2001lU c2001lU = new C2001lU(this.h, d());
        this.g = c2001lU;
        return c2001lU;
    }

    @Override // com.android.tools.r8.internal.AbstractC2172nU, java.util.ListIterator, java.util.Iterator
    public final void remove() {
        super.remove();
        this.g.b = -1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
