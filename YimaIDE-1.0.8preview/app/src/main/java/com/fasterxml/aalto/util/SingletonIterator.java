package com.fasterxml.aalto.util;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class SingletonIterator implements Iterator<String> {
    private boolean _done = false;
    private final String _value;

    public SingletonIterator(String str) {
        this._value = str;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return !this._done;
    }

    @Override // java.util.Iterator
    public String next() {
        if (this._done) {
            z0e.a();
            return null;
        }
        this._done = true;
        return this._value;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
