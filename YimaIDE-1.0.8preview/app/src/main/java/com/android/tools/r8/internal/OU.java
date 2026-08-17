package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class OU extends KU implements NU {
    @Override // com.android.tools.r8.internal.KU
    public final Object clone() {
        return PU.a;
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return null;
    }

    @Override // java.util.SortedSet
    public final Object first() {
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return PU.a;
    }

    @Override // java.util.SortedSet
    public final Object last() {
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        return PU.a;
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return PU.a;
    }
}
