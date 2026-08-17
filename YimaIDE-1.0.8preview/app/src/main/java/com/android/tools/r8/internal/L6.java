package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L6 extends AbstractC1789j implements Serializable, Cloneable {
    @Override // com.android.tools.r8.internal.AbstractC1789j
    public final boolean a() {
        throw new NoSuchElementException();
    }

    public final Object clone() {
        return O6.a;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return false;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return false;
    }

    @Override // com.android.tools.r8.internal.K6
    public final boolean n() {
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return 0;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return -1;
    }
}
