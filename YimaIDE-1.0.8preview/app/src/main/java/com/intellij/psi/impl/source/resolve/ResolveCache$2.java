package com.intellij.psi.impl.source.resolve;

import com.intellij.util.containers.ConcurrentWeakKeySoftValueHashMap;
import java.lang.ref.ReferenceQueue;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class ResolveCache$2<K, V> extends ConcurrentWeakKeySoftValueHashMap<K, V> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 2 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "queue";
        } else if (i == 2) {
            objArr[0] = "com/intellij/psi/impl/source/resolve/ResolveCache$2";
        } else if (i != 3) {
            objArr[0] = "value";
        } else {
            objArr[0] = "key";
        }
        if (i != 2) {
            objArr[1] = "com/intellij/psi/impl/source/resolve/ResolveCache$2";
        } else {
            objArr[1] = "createValueReference";
        }
        if (i != 2) {
            if (i != 3) {
                objArr[2] = "createValueReference";
            } else {
                objArr[2] = "get";
            }
        }
        String str2 = String.format(str, objArr);
        if (i == 2) {
            throw new IllegalStateException(str2);
        }
    }

    public ResolveCache$2(int i, float f, int i2) {
        super(i, f, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.intellij.util.containers.ConcurrentWeakKeySoftValueHashMap
    public ConcurrentWeakKeySoftValueHashMap.ValueReference<K, V> createValueReference(V v, ReferenceQueue<? super V> referenceQueue) {
        if (v == 0) {
            $$$reportNull$$$0(0);
        }
        if (referenceQueue == null) {
            $$$reportNull$$$0(1);
        }
        ConcurrentWeakKeySoftValueHashMap.ValueReference<K, V> valueReferenceAccess$100 = (v == ResolveCache.access$000() || ((v instanceof Object[]) && ((Object[]) v).length == 0)) ? ResolveCache.access$100(v) : super.createValueReference(v, referenceQueue);
        if (valueReferenceAccess$100 == null) {
            $$$reportNull$$$0(2);
        }
        return valueReferenceAccess$100;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override // com.intellij.util.containers.ConcurrentWeakKeySoftValueHashMap, java.util.Map
    public V get(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(3);
        }
        V v = (V) super.get(obj);
        if (v == ResolveCache.access$000()) {
            return null;
        }
        return v;
    }

    @Override // java.util.Map
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // com.intellij.util.containers.ConcurrentWeakKeySoftValueHashMap, java.util.Map
    public V put(K k, V v) {
        if (v == null) {
            v = (V) ResolveCache.access$000();
        }
        return (V) super.put(k, v);
    }
}
