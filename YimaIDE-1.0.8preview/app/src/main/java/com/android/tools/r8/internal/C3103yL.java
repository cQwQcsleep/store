package com.android.tools.r8.internal;

import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yL, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3103yL implements ListIterator {
    public boolean b;
    public final /* synthetic */ ListIterator c;
    public final /* synthetic */ C3188zL d;

    public C3103yL(C3188zL c3188zL, ListIterator listIterator) {
        this.d = c3188zL;
        this.c = listIterator;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        this.c.add(obj);
        this.c.previous();
        this.b = false;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.c.hasPrevious();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.c.hasNext();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        if (this.c.hasPrevious()) {
            this.b = true;
            return this.c.previous();
        }
        z0e.a();
        return null;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        C3188zL c3188zL = this.d;
        int iNextIndex = this.c.nextIndex();
        int size = c3188zL.b.size();
        DX.b(iNextIndex, size);
        return size - iNextIndex;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (this.c.hasNext()) {
            this.b = true;
            return this.c.next();
        }
        z0e.a();
        return null;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return nextIndex() - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        if (!this.b) {
            k2d.a("no calls to next() since the last call to remove()");
        } else {
            this.c.remove();
            this.b = false;
        }
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        if (this.b) {
            this.c.set(obj);
        } else {
            g33.a();
        }
    }
}
