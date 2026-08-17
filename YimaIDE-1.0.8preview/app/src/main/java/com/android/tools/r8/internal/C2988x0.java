package com.android.tools.r8.internal;

import java.util.List;
import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2988x0 extends C2816v0 implements ListIterator {
    public final /* synthetic */ C3073y0 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2988x0(C3073y0 c3073y0, int i) {
        super(c3073y0, ((List) c3073y0.c).listIterator(i));
        this.e = c3073y0;
    }

    public final ListIterator a() {
        this.d.b();
        if (this.d.c == this.c) {
            return (ListIterator) this.b;
        }
        a1e.a();
        return null;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        boolean zIsEmpty = this.e.isEmpty();
        a().add(obj);
        C3073y0 c3073y0 = this.e;
        c3073y0.g.g++;
        if (zIsEmpty) {
            c3073y0.a();
        }
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return a().hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return a().nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return a().previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return a().previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        a().set(obj);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2988x0(C3073y0 c3073y0) {
        super(c3073y0);
        this.e = c3073y0;
    }
}
