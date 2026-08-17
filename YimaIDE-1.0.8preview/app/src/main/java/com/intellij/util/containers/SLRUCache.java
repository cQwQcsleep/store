package com.intellij.util.containers;

import com.intellij.util.containers.hash.EqualityPolicy;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class SLRUCache<K, V> extends SLRUMap<K, V> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2) ? 2 : 3];
        if (i == 1 || i == 2) {
            objArr[0] = "com/intellij/util/containers/SLRUCache";
        } else if (i == 3 || i == 4) {
            objArr[0] = "valueProducer";
        } else {
            objArr[0] = "hashingStrategy";
        }
        if (i == 1 || i == 2) {
            objArr[1] = "get";
        } else {
            objArr[1] = "com/intellij/util/containers/SLRUCache";
        }
        if (i != 1 && i != 2) {
            if (i == 3) {
                objArr[2] = "slruCache";
            } else if (i != 4) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "create";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SLRUCache(int i, int i2, EqualityPolicy<? super K> equalityPolicy) {
        super(i, i2, equalityPolicy);
        if (equalityPolicy == null) {
            $$$reportNull$$$0(0);
        }
    }

    public abstract V createValue(K k);

    @Override // com.intellij.util.containers.SLRUMap
    public V get(K k) {
        V ifCached = getIfCached(k);
        if (ifCached != null) {
            return ifCached;
        }
        V vCreateValue = createValue(k);
        put(k, vCreateValue);
        if (vCreateValue == null) {
            $$$reportNull$$$0(2);
        }
        return vCreateValue;
    }

    public V getIfCached(K k) {
        return (V) super.get(k);
    }
}
