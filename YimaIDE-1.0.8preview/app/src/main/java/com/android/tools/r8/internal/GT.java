package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GT extends LT implements HU {
    public JT g;
    public final /* synthetic */ NT h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GT(NT nt) {
        super(nt);
        this.h = nt;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        JT jt = new JT(this.h, b());
        this.g = jt;
        return jt;
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        JT jt = new JT(this.h, d());
        this.g = jt;
        return jt;
    }

    @Override // com.android.tools.r8.internal.LT, java.util.ListIterator, java.util.Iterator
    public final void remove() {
        super.remove();
        this.g.b = -1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
