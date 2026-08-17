package org.snakeyaml.engine.v2.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class Tuple<T, K> {
    private final T _1;
    private final K _2;

    public Tuple(T t, K k) {
        this._1 = t;
        this._2 = k;
    }

    public T _1() {
        return this._1;
    }

    public K _2() {
        return this._2;
    }
}
