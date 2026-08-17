package com.intellij.util.containers;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class SingletonIteratorBase<T> implements Iterator<T> {
    private boolean myVisited;

    public abstract void checkCoModification();

    public abstract T getElement();

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.myVisited;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (this.myVisited) {
            z0e.a();
            return null;
        }
        this.myVisited = true;
        checkCoModification();
        return getElement();
    }
}
