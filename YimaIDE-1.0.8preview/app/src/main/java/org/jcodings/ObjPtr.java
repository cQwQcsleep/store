package org.jcodings;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class ObjPtr<T> {
    static final ObjPtr<Void> NULL = new ObjPtr<>();
    public T p;

    public ObjPtr(T t) {
        this.p = t;
    }

    public ObjPtr() {
        this(null);
    }
}
