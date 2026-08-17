package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.J5;
import java.util.ListIterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class J5 implements InterfaceC0916Vw {
    public final ListIterator b;

    public J5(H5 h5, final AbstractC0890Uw abstractC0890Uw) {
        this.b = h5.k().listIterator();
        a(new Predicate() { // from class: b97
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return J5.c(abstractC0890Uw, (AbstractC0890Uw) obj);
            }
        });
    }

    public static /* synthetic */ boolean c(AbstractC0890Uw abstractC0890Uw, AbstractC0890Uw abstractC0890Uw2) {
        return abstractC0890Uw2 == abstractC0890Uw;
    }

    @Override // java.util.Iterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public AbstractC0890Uw next() {
        return (AbstractC0890Uw) this.b.next();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b.hasNext();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0916Vw
    public final boolean hasPrevious() {
        return this.b.hasPrevious();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0916Vw
    public final AbstractC0890Uw previous() {
        return (AbstractC0890Uw) this.b.previous();
    }

    public J5(H5 h5) {
        this.b = h5.k().listIterator();
    }

    public J5(H5 h5, int i) {
        this.b = h5.k().listIterator(i);
    }
}
