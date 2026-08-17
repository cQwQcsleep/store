package com.android.tools.r8.internal;

import com.android.tools.r8.internal.H5;
import com.android.tools.r8.internal.L5;
import java.util.ListIterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class L5 implements ListIterator<H5> {
    public final C0705Nt b;
    public final ListIterator c;
    public H5 d;

    public L5(C0705Nt c0705Nt) {
        this.b = c0705Nt;
        this.c = c0705Nt.d.listIterator();
    }

    public final void a(final H5 h5) {
        this.d = (H5) this.c.next();
    }

    @Override // java.util.ListIterator
    public final void add(H5 h5) {
        this.c.add(h5);
    }

    public final H5 b(final H5 h5) {
        return (H5) BC.a((ListIterator) this, new Predicate() { // from class: ll8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return L5.b(h5, (H5) obj);
            }
        });
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.c.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.c.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        H5 h5 = (H5) this.c.next();
        this.d = h5;
        return h5;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.c.nextIndex();
    }

    @Override // java.util.ListIterator
    public final H5 previous() {
        H5 h5 = (H5) this.c.previous();
        this.d = h5;
        return h5;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.c.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        H5 h5 = this.d;
        if (h5 == null) {
            g33.a();
            return;
        }
        K5 k5A = h5.a(this.b);
        while (k5A.hasNext()) {
            k5A.next().Q0();
            k5A.remove();
        }
        this.c.remove();
        this.d = null;
    }

    @Override // java.util.ListIterator
    public final void set(H5 h5) {
        this.c.set(h5);
    }

    public static /* synthetic */ boolean b(H5 h5, H5 h6) {
        return h6 == h5;
    }

    public L5(C0705Nt c0705Nt, int i) {
        this.b = c0705Nt;
        this.c = c0705Nt.d.listIterator(i);
    }

    public final H5 a() {
        H5 h5 = (H5) this.c.next();
        this.d = h5;
        return h5;
    }

    public static /* synthetic */ boolean a(H5 h5, H5 h6) {
        return h6 == h5;
    }
}
