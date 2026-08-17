package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1726iA extends AbstractC1025a0 implements Serializable, Cloneable {
    public final Object clone() {
        return AbstractC1895kA.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1045aA
    public final int g() {
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return false;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return false;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return 0;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return -1;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1640hA
    public final int q() {
        throw new NoSuchElementException();
    }
}
