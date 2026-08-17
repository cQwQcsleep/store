package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MultiHashtable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class MultiHashtable<K, V> {
    static final long serialVersionUID = -6151608290510033572L;
    private final Map<K, Set<V>> map = new HashMap();
    private boolean modifiable = true;

    public static /* synthetic */ Set a(Object obj) {
        return new HashSet();
    }

    public void makeUnmodifiable() {
        this.modifiable = false;
    }

    public V maps(K k, V v) {
        Set<V> set;
        if (k != null && (set = this.map.get(k)) != null) {
            for (V v2 : set) {
                if (v2.equals(v)) {
                    return v2;
                }
            }
        }
        return null;
    }

    public Set<V> put(K k, V v) {
        if (!this.modifiable) {
            c41.a("The MultiHashtable instance is not modifiable.");
            return null;
        }
        Set<V> setComputeIfAbsent = this.map.computeIfAbsent(k, new Function() { // from class: e8a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MultiHashtable.a(obj);
            }
        });
        setComputeIfAbsent.add(v);
        return setComputeIfAbsent;
    }
}
