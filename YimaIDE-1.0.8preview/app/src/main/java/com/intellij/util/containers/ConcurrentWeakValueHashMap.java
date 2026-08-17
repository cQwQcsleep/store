package com.intellij.util.containers;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class ConcurrentWeakValueHashMap<K, V> extends ConcurrentRefValueHashMap<K, V> {

    public static final class MyWeakReference<K, V> extends WeakReference<V> implements ConcurrentRefValueHashMap.ValueReference<K, V> {
        private final K key;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 3 ? 3 : 2];
            if (i == 1) {
                objArr[0] = "referent";
            } else if (i == 2) {
                objArr[0] = "q";
            } else if (i != 3) {
                objArr[0] = "key";
            } else {
                objArr[0] = "com/intellij/util/containers/ConcurrentWeakValueHashMap$MyWeakReference";
            }
            if (i != 3) {
                objArr[1] = "com/intellij/util/containers/ConcurrentWeakValueHashMap$MyWeakReference";
            } else {
                objArr[1] = "getKey";
            }
            if (i != 3) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 3) {
                throw new IllegalStateException(str2);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private MyWeakReference(K k, V v, ReferenceQueue<V> referenceQueue) {
            super(v, referenceQueue);
            if (k == null) {
                $$$reportNull$$$0(0);
            }
            if (v == null) {
                $$$reportNull$$$0(1);
            }
            if (referenceQueue == null) {
                $$$reportNull$$$0(2);
            }
            this.key = k;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && MyWeakReference.class == obj.getClass()) {
                ConcurrentRefValueHashMap.ValueReference valueReference = (ConcurrentRefValueHashMap.ValueReference) obj;
                V v = get();
                Object obj2 = valueReference.get();
                if (this.key.equals(valueReference.getKey()) && v != null && v.equals(obj2)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.intellij.util.containers.ConcurrentRefValueHashMap.ValueReference
        public K getKey() {
            K k = this.key;
            if (k == null) {
                $$$reportNull$$$0(3);
            }
            return k;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "key";
        } else {
            objArr[0] = "value";
        }
        objArr[1] = "com/intellij/util/containers/ConcurrentWeakValueHashMap";
        objArr[2] = "createValueReference";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public ConcurrentWeakValueHashMap(BiConsumer<? super ConcurrentMap<K, V>, ? super K> biConsumer) {
        super(biConsumer);
    }

    @Override // com.intellij.util.containers.ConcurrentRefValueHashMap
    public ConcurrentRefValueHashMap.ValueReference<K, V> createValueReference(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(0);
        }
        if (v == null) {
            $$$reportNull$$$0(1);
        }
        return new MyWeakReference(k, v, this.myQueue);
    }
}
