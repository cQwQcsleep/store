package com.intellij.util.containers;

import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface IntObjectMap<V> {

    public interface Entry<V> {
        int getKey();

        V getValue();
    }

    Set<Entry<V>> entrySet();

    V get(int i);

    V put(int i, V v);

    Collection<V> values();
}
