package com.intellij.util.containers;

import com.intellij.util.IncorrectOperationException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class SingletonIterator<T> extends SingletonIteratorBase<T> {
    private final T myElement;

    public SingletonIterator(T t) {
        this.myElement = t;
    }

    @Override // com.intellij.util.containers.SingletonIteratorBase
    public void checkCoModification() {
    }

    @Override // com.intellij.util.containers.SingletonIteratorBase
    public T getElement() {
        return this.myElement;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // java.util.Iterator
    public void remove() throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }
}
