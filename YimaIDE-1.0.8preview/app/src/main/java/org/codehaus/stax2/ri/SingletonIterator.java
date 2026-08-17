package org.codehaus.stax2.ri;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class SingletonIterator<T> implements Iterator<T> {
    private boolean _done = false;
    private final T _value;

    @Deprecated
    public SingletonIterator(T t) {
        this._value = t;
    }

    public static <T> SingletonIterator<T> create(T t) {
        return new SingletonIterator<>(t);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return !this._done;
    }

    @Override // java.util.Iterator
    public T next() {
        if (this._done) {
            z0e.a();
            return null;
        }
        this._done = true;
        return this._value;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Can not remove item from SingletonIterator.");
    }
}
